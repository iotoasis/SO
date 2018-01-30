package com.pineone.icbms.so.web.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pineone.icbms.so.interfaces.database.dao.SmartHelperDao;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.database.model.SmartHelperForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;
import com.pineone.icbms.so.interfaces.messagequeue.model.OrchestrationServiceForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.orchestrationservice.DefaultOrchestrationService;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.web.model.smarthelper.ReqSmartHelper;

import lombok.Getter;
import lombok.Setter;

@Service
public class SmartHelperService implements ISmartHelperService {
    @Autowired
    //IDataBaseStore dataBaseStore;
    DataBaseStore dataBaseStore;

    @Autowired
    SmartHelperDao smartHelperDao;

    @Getter @Setter
    TrackingEntity tracking;

    protected Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String callOsService(ReqSmartHelper req, HttpServletRequest request, TrackingEntity trackingEntity) {

		String resultMsg=null;
        setTracking((TrackingEntity) request.getSession().getAttribute("tracking"));
		
		String locId = req.getLocation();

		String serviceName = req.getServiceName();
		String contextModelId="SmartHelper";
		String contextModelName="스마트헬퍼";
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

		LocationForDB locForDB = dataBaseStore.getLocationById(locId);
		if (locForDB==null) {
            log.error("Error Invalid LocationId={}", locId);
			return null;
		}
		String locationUri =  locForDB.getUri();
        
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
        
		SmartHelperForDB param = new SmartHelperForDB();
		param.setLocationId(locId);
		param.setName(serviceName);
		List <SmartHelperForDB> smartHelperForDbList = smartHelperDao.retrieveByParam(param);
		
		if (smartHelperForDbList==null || smartHelperForDbList.size()==0) { //일치되는 자료 없음
			return null;
		}

		
		//2개이상 OS가 연결되면 무시? 에러?
		
		//첫번째 OS만 유효
		for (SmartHelperForDB item: smartHelperForDbList) {

            getTracking().setProcessId(item.getId());
            getTracking().setProcessName(item.getName());
            TrackingProducer.send(getTracking());

            // grib session profile
            SessionEntity sessionProfile = new SessionEntity();
            sessionProfile.setId(sessionId);
            sessionProfile.setProfileKey(item.getId());
            //sessionProfile.setProfileName(item.getName());
            sessionProfile.setPriorityKey("LOW");
            log.debug("session profile : {}", sessionProfile);
            dataBaseStore.updateSessionData(sessionProfile);
			
			
			String osId = item.getOrchestrationServiceId();

			DefaultOrchestrationService orchestrationService = new DefaultOrchestrationService();
			orchestrationService.setId(osId);
			publishOrchestrationService(orchestrationService, locationUri, contextModelId);

			
			String locName =  locForDB.getName();
			
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy년 MM월 dd일 E요일 a hh시",  Locale.KOREAN); 
			String time = formatter.format(date);
			
			resultMsg = item.getSuccessMsg();
			resultMsg = resultMsg.replace("{{loc}}", locName);
			resultMsg = resultMsg.replace("{{time}}", time);
			break;
		}

		return resultMsg;
	}

	
    /**
     * publish a OrchestrationService to MQ.<BR/>
     * 서비스 객체를 메시지큐 객체로 변환한다
     * @param orchestrationService IGenericOrchestrationService
     */
    private void publishOrchestrationService(IGenericOrchestrationService orchestrationService, String locationUri, String contextModelId) {
        //generate a OrchestrationServiceForMQ model
        OrchestrationServiceForMQ orchestrationServiceForMQ = ModelMapper.toOrchestrationServiceForMQ(orchestrationService);

        orchestrationServiceForMQ.setTrackingEntity(getTracking());

        // location id =? location uri
        orchestrationServiceForMQ.addState(Const.LOCATION_URI, locationUri);
        orchestrationServiceForMQ.addState(Const.CONTEXTMODEL_ID, contextModelId);
        
        //generate to string.
        String jsonString = toJsonString(orchestrationServiceForMQ);
        
        //publish by producer
        publishToMq(jsonString);
    }

    /**
     * OrchestrationServiceForMQ to json String.<BR/>
     *
     * @param orchestrationServiceForMQ OrchestrationServiceForMQ
     * @return json String
     */
    private String toJsonString(OrchestrationServiceForMQ orchestrationServiceForMQ) {
        return ModelMapper.writeJsonString(orchestrationServiceForMQ);
    }

    /**
     * publish a data.<BR/>
     * orchestrationservice 토픽으로 메시지 전송
     * @param data data
     * @return result
     */
    public void publishToMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "orchestrationservice");
        producerHandler.send(data);
        producerHandler.close();
    }
}
