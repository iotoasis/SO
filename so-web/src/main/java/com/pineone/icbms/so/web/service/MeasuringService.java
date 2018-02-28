package com.pineone.icbms.so.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pineone.icbms.so.interfaces.database.dao.OrchestrationServiceDao;
import com.pineone.icbms.so.interfaces.database.model.DeviceTypeForDB;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import com.pineone.icbms.so.interfaces.database.model.MeasuringVoForDB;
import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.web.model.MeasuringData;
import com.pineone.icbms.so.web.model.measure.ReqMeasuring;

import lombok.Getter;
import lombok.Setter;

@Service
public class MeasuringService implements IMeasuringService {
    @Autowired
    //IDataBaseStore dataBaseStore;
    DataBaseStore dataBaseStore;

    //@Autowired
    //protected DatabaseManager databaseManager;

    @Autowired
    OrchestrationServiceDao orchestrationServiceDao;

    @Getter @Setter
    TrackingEntity tracking;

    protected Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Object getMeasuredData(ReqMeasuring req, HttpServletRequest request, TrackingEntity trackingEntity) {

		//입력 파라미터
		String locId = req.getLocation();          //설치 위치
		String serviceName = req.getServiceName(); //OS 이름

		//설치 장소
		LocationForDB locForDB = dataBaseStore.getLocationById(locId);
		String locationUri =  locForDB.getUri();
		String locName =  locForDB.getName();

		
		//1) OS Name으로 osId얻기
		OrchestrationServiceForDB param = new OrchestrationServiceForDB();
		param.setName(serviceName);
		
		List<OrchestrationServiceForDB> osForDbList =  orchestrationServiceDao.retrieve(param);
		if (osForDbList==null || osForDbList.size()==0) { //일치되는 자료 없음
			log.error("Service Info ({}) not found", serviceName); //orchestration_service : name
			return null;
		}
		OrchestrationServiceForDB osForDb = osForDbList.get(0);
		String osId = osForDb.getId();

        //2) os id 로 MeasuringVoForDB 목록을 조회한다
		//  osId로 rule_body/rule_item에서 vo list 얻기 : (rule_item의 functionality_id가 FUNCTIONALITY_TYPE_MEASURING인 값만 선택)
		// cvo_type이 NONDEVICE_CVO인 경우에는 noncvo테이블을 참조하여 확장
        log.debug("getRuleBodyListByOsId : {}", osId);
        List<MeasuringVoForDB> measuringVoForDBList = dataBaseStore.getMeasuringVoList(osId);

        // 측정데이타를 저장할 변수 생성
        List<MeasuringData> measuringDataList = new ArrayList<>();
        		
        //3) MeasuringVoForDB의 item별로 동작
        for (MeasuringVoForDB measuringVoItem:measuringVoForDBList) {

        	String cvoBaseId = measuringVoItem.getBaseCvoId();
        	String cvoType = measuringVoItem.getCvoType();

            // BaseCvoId 부터 유효 VO 목록을 가져온다.
           	List<VirtualObjectForDB> validVoList = dataBaseStore.getVirtualObjectListByCompositeVirtualObjectId(cvoBaseId);
           	if (validVoList.size()==0) {
                log.error("Not exist vo list by cvoBase={}", cvoBaseId);
                continue;
           	}
       		String vId = measuringVoItem.getVoId();
       		String aspectUri = null;
        	String voDesc = null;
       		
           	//VO값이 유효한 Vo인지 검사한다.
       		for (VirtualObjectForDB validVo:validVoList) {
       			if (vId.equals(validVo.getId())){
		        	aspectUri = validVo.getAspectUri(); //첫번째 VO만 참조하여 URI를 가져옴
		        	voDesc = validVo.getDescription();
		        	break;
       			}
       		}
       		
       		//유효하지 않으면
       		if (aspectUri == null ) { //SKIP
                log.error("Not exist aspectUri by list by vId={}", vId);
                continue;
       		}

       		// 4) cvo_type별로 sda에서 값 읽기
       		List<ContextModelContent> contentList = null;
            //4-1) CVO_TYPE_DEVICETYPE or CVO_TYPE_DEVICETYPE_ASPECT
            if (cvoType.equals("CVO_TYPE_DEVICETYPE") || cvoType.equals("CVO_TYPE_DEVICETYPE_ASPECT")) {
                String physicalDeviceTypeId = measuringVoItem.getPhysicalDeviceTypeId();
           
	        	//PysicalDeviceTypeUri
                DeviceTypeForDB typeDb = dataBaseStore.retrieveDeviceTypeById(physicalDeviceTypeId);
	        	if (typeDb==null) {
	        		log.error("error : Device Type info is not exist : physicalDeviceTypeId={}", physicalDeviceTypeId);
	        		continue;
	        	}
	        	String physicalDeviceTypeUri = typeDb.getPhysicalDeviceTypeUri();

	        	contentList = new SdaManager().getMeasuringValueByLocDeviceTypeAspect(locationUri, physicalDeviceTypeUri, aspectUri);
            }
            //4-2) CVO_TYPE_ASPECT
            else if (cvoType.equals("CVO_TYPE_ASPECT")) {
            
            	contentList = new SdaManager().getMeasuringValueByLocAspect(locationUri, aspectUri);
            }
            //4-3) CVO_TYPE_DEVICEID
            else if (cvoType.equals("CVO_TYPE_DEVICEID")) {

            	String deviceUri = measuringVoItem.getDeviceId();
            	contentList = new SdaManager().getMeasuringValueByDevIdAspect(deviceUri, aspectUri);
            	
            } else {
            	// SKIP
            }
            
            if (contentList != null) {
	            for (ContextModelContent contentItem:contentList) {
	            	
	            	MeasuringData md = new MeasuringData();
	            	
	            	md.setDevId(contentItem.getDeviceUri());
	    	        md.setDevName(contentItem.getDeviceName());
	    	        md.setDevTypeDesc(contentItem.getDeviceTypeDesc());
	    	        md.setAspectName(voDesc);
	    	        md.setMeasuringValue(contentItem.getValue());

	    	        measuringDataList.add(md);
	            }
            }
        }
		
/*		
		String contextModelId="measuring";
		String contextModelName="Measureing";

		setTracking((TrackingEntity) request.getSession().getAttribute("tracking"));
    	String sessionId = trackingEntity.getSessionId();
		
        // grib session
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(sessionId);
        sessionEntity.setContextmodelKey(contextModelId);
        sessionEntity.setContextmodelName(contextModelName);
        sessionEntity.setContextmodelResult("NotHappen");
        sessionEntity.setProfileName(serviceName);
        //sessionEntity.setPriorityKey("LOW");
        log.debug("session : {}", sessionEntity);
        dataBaseStore.createSessionData(sessionEntity);
        
		
        // grib session location
        SessionEntity sessionLocation = new SessionEntity();
        sessionLocation.setId(sessionId);
        sessionLocation.setLocationId(locationUri);
        log.debug("session location : {}", sessionLocation);
        try {
        	dataBaseStore.createSessionDataLocation(sessionLocation);
        }catch(Exception e) {
            log.error("Error createSessionDataLocation : error={},\n sessionLocation={}", e.getMessage(), sessionLocation);
        }
*/

        return measuringDataList;
	}
	
}
