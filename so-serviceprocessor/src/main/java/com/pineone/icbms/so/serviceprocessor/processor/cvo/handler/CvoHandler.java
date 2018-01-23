package com.pineone.icbms.so.serviceprocessor.processor.cvo.handler;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.DeviceTypeForDB;
import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import com.pineone.icbms.so.interfaces.database.model.LocationForDB;
import com.pineone.icbms.so.interfaces.database.model.NonDeviceCvoForDB;
import com.pineone.icbms.so.interfaces.database.model.RuleItemForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.CompositeVirtualObjectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.model.VirtualObjectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.AGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.DefaultVirtualObject;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.AGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.DefaultCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;
import com.sun.prism.Image;

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Cvo handler.<BR/>
 * 서비스 핸들러 : os 와 연결된 cvo 와 vo를 셋팅하고 virtualobject 큐로 메시지를 전송한다.
 * Created by uni4love on 2017. 1. 20..
 */
public class CvoHandler extends AProcessHandler<IGenericCompositeVirtualObject> {

    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public CvoHandler(IDatabaseManager databaseManager) {
        super(databaseManager);
    }

    /**
     * OrchestrationService handler.<BR/>
     *
     * @param compositeVirtualObject IGenericCompositeVirtualObject
     */
    public void handle(IGenericCompositeVirtualObject _compositeVirtualObject) {

    	String cvoBaseId=null;
    	
        getTracking().setProcess(getClass().getSimpleName());
        
        AGenericCompositeVirtualObject compositeVirtualObject = (AGenericCompositeVirtualObject)_compositeVirtualObject;
  
        String cvoType = compositeVirtualObject.getCvoType();
    	String sessionId = getTracking().getSessionId();
        
/*
	CVO_TYPE_DEVICEID		 	0x000000001
	CVO_TYPE_DEVICETYPE		 	0x000000010
	CVO_TYPE_ASPECT			 	0x000000100
	CVO_TYPE_NONEDEVICE		 	0x000010000
	CVO_TYPE_DEVICETYPE_ASPECT	0x000000110
	CVO_TYPE_DYNAMICDEVICE	 	0x000001000
        
 */
        cvoBaseId = compositeVirtualObject.getBaseCvoId();

        // get CVO list
        // cvoType이 CVO_TYPE_NONEDEVICE 이면 gcvo로 부터 cvo를 읽어와서 cvo별로 cvo 재호출
        if (cvoType.equals("CVO_TYPE_NONEDEVICE")) {
        	String osId = compositeVirtualObject.getOsId();
        	log.debug("retrieveNonDeviceCvoList : {}, {}", cvoBaseId, osId);
        	List<NonDeviceCvoForDB> compositeVirtualObjectForDBList = databaseManager.retrieveNonDeviceCvoList(cvoBaseId, osId);

        	String ruleBodyId = compositeVirtualObject.getId();
        	String locationId = compositeVirtualObject.getLocationId();
        	
        	//String sessionId = getTracking().getSessionId();
        	String cvoIds = null;

        	List<IGenericCompositeVirtualObject> builtCvoList = new ArrayList<>();
	        
	        for (NonDeviceCvoForDB gcvoDbItem:compositeVirtualObjectForDBList) {

	        	//String builtCvoId = cvoBaseId + "-" +deviceId.substring(deviceId.lastIndexOf("/")+1,deviceId.length()) + "-CVO";
	            String cvoId = gcvoDbItem.getId();
	            if (cvoIds==null)
	            	cvoIds = gcvoDbItem.getBaseCvoId();
	            else
	            	cvoIds = cvoIds +","+ gcvoDbItem.getBaseCvoId();
	            
	        	AGenericCompositeVirtualObject builtCvo = new DefaultCompositeVirtualObject();
/*
	        	builtCvo.setLocationId(locationId);          //locationId
	       		builtCvo.setCvoType("CVO_TYPE_ASPECT");      //cvoType
	       		builtCvo.setBaseCvoId(cvoId);			 	 //cvoBase
	       		builtCvo.setId(ruleBodyId);					 
*/
	        	builtCvo.setId(cvoId);
	        	//builtCvo.setName(gcvoDbItem.getId());
	        	builtCvo.setBaseCvoId(gcvoDbItem.getBaseCvoId());
	        	builtCvo.setCvoType(gcvoDbItem.getCvoType());
	        	builtCvo.setDeviceId(gcvoDbItem.getDeviceId());
	        	builtCvo.setLocationId(gcvoDbItem.getLocationId());
	        	builtCvo.setPhysicalDeviceTypeId(gcvoDbItem.getPhysicalDeviceTypeId());
	        	builtCvo.setOsId(gcvoDbItem.getOrgOsId());

	        	builtCvoList.add(builtCvo);
	       	}


	        cvoIds = "[" + cvoIds + "]";
        	SessionEntity sessionCvo = databaseManager.getSessionData(sessionId);
        	String prevServiceKey = sessionCvo.getServiceKey();
        	if (prevServiceKey!=null)
        		cvoIds = prevServiceKey + "," + cvoIds;

	        sessionCvo.setServiceKey(cvoIds);
            databaseManager.updateSessionData(sessionCvo);

        	certLog.debug("=============START(CVO_TYPE_NONEDEVICE)==============");
        	certLog.debug("sessionID : " + sessionId);
        	certLog.debug("baseCVOID : " + cvoBaseId);
        	certLog.debug("=======================END============================");
            
            handleCompositeVirtualObjectList(builtCvoList, compositeVirtualObject.getStateStore());

            return;
        }
/*        
        base_cvo_id : rule_item의 vo목록이 유효한지 검사에 사용
        	base_cvo_id에 의하여 cvo_vo에서 vo_id를 가져온후 rule_item의 vo_id의 유효성 판단
        cvo_type
        	physical_device_type_id : uri = SDA에서 DeviceId를 가져올때 사용할 파라미터
        	location_id : uri = device가 설치된 위치. null인 경우 CM이 발생한 위치의 값을 사용
        	
*/
       	
       	//유효 VO List얻기
       	List<VirtualObjectForDB> validVirtualObjectForDBList = new ArrayList<VirtualObjectForDB>();

       	//1)ruleBody와 연결된 rule Item 정보를 가져온다.
        String ruleBodyId = compositeVirtualObject.getId();
       	List<RuleItemForDB> ruleItemForDBList = databaseManager.getRuleItemListByRuleBodyId(ruleBodyId);
       	

        //2)BaseCvo Id 부터 VO 목록을 가져온다.
       	List<VirtualObjectForDB> validVoList = databaseManager.getVirtualObjectListByCompositeVirtualObjectId(cvoBaseId);
       	if (validVoList.size()==0) {
            log.error("Not exist vo list by cvoBase={}", cvoBaseId);
       	}
       	
       	//3)rule Item중에서 유효한 Vo List만 가려낸다.
       	for (RuleItemForDB item : ruleItemForDBList) {
       		String vId = item.getVoId();
       		
       		for (VirtualObjectForDB validVo:validVoList) {
       			if (vId.equals(validVo.getId())){
		       		VirtualObjectForDB vo = new VirtualObjectForDB(); 
		       		vo.setAspectUri(item.getAspectUri());
		       		vo.setFunctionalityId(item.getFuntionalityId());
		       		vo.setFunctionalityUri(item.getFuntionalityUri());
		       		vo.setId(item.getVoId());
		       		vo.setVoValueType(item.getVoValueType());
		       		vo.setVoValue(item.getVoValue());
		       		validVirtualObjectForDBList.add(vo);
       			}
       		}
       	}
       	
       	if (validVirtualObjectForDBList.size()>0) {
           	//4) vo목록를 IGenericVirtualObject 형태로 변환
            List<IGenericVirtualObject> commonVoList 
    			= ModelMapper.toVirtualObjectListByVirtualObjectForDbList(validVirtualObjectForDBList);

            //VO의 마지막표시
           	int voLast = commonVoList.size()-1;
           	if ( voLast>= 0)
           		commonVoList.get(voLast).setIsLast("Y");
       		
       		//1) vo정보로 부터 aspect URI 얻기
       		VirtualObjectForDB vo = validVirtualObjectForDBList.get(0); //나머지는 동일하므로 첫번째 VO만 참조하여 URI를 가져옴
       		
       		String aspectUri = vo.getAspectUri(); //첫번째 VO만 참조하여 URI를 가져옴
       		String functionalityId = vo.getFunctionalityId();
       		FunctionalityForDB functionForDb = databaseManager.getFunction(functionalityId);
       		String functionalityUri = functionForDb.getUri();

       		//2) LocationUri
	       	String locationUri;
	       	String locationId = compositeVirtualObject.getLocationId();
	       	//CVO에 Location없으면 상황발생 상황의 locationId 사용
	       	if (locationId==null) {
	           	//CM이 발생한 Location 정보 얻기
	           	IGenericStateStore<String, ?> state =compositeVirtualObject.getStateStore();
	           	locationUri = (String)state.getValue(Const.LOCATION_URI);
	       	} else {
	       		LocationForDB locForDB = databaseManager.getLocationById(locationId);
	       		locationUri = locForDB.getUri();
	       	}
       		
           	//3) CvoType를 검사하여 DeviceID Type이 아닌 경우 DeviceID Type의 CVO를 만든다.
            List<IGenericCompositeVirtualObject> builtCvoList = new ArrayList<>();

       		List<String> deviceList=null;
	        if (cvoType.equals("CVO_TYPE_DEVICEID")) {
	        	String deviceId = compositeVirtualObject.getDeviceId();
	        	
	        	deviceList = new ArrayList<>();
	        	deviceList.add(deviceId);
	        	
            	certLog.debug("=============START(CVO_TYPE_DEVICEID)==============");
            	certLog.debug("sessionID : " + sessionId);
            	certLog.debug("deviceID : " + deviceId);
            	certLog.debug("=======================END============================");
	        } 
	        else {
	        	//3-1) SDA로 부터 Device ID 목록 가져오기
	        	if (cvoType.equals("CVO_TYPE_DEVICETYPE") || cvoType.equals("CVO_TYPE_DEVICETYPE_ASPECT")) {
	        		String physicalDeviceTypeId = compositeVirtualObject.getPhysicalDeviceTypeId();
	        		if (physicalDeviceTypeId==null) {
	        			log.error("error : physicalDeviceTypeId is not exist. cvoType={}", cvoType);
	        			return;
	        		}
	        		DeviceTypeForDB typeDb = databaseManager.retrieveDeviceTypeById(physicalDeviceTypeId);
	        		if (typeDb==null) {
	        			log.error("error : Device Type info is not exist : physicalDeviceTypeId={}", physicalDeviceTypeId);
	        			return;
	        		}

	        		String physicalDeviceTypeUri = typeDb.getPhysicalDeviceTypeUri();
	        		
			       	//DeviceId 목록을 가져옴
			       	deviceList = new SdaManager().getDeviceListByLoc_DeviceType_Aspect_Func(locationUri, physicalDeviceTypeUri, aspectUri, functionalityUri);
	            	log.info("getDeviceListByLoc_DeviceType_Aspect_Func :\nlocationUri={}\n physicalDeviceTypeUri={}\n aspectUri={}\n functionalityUri={}", locationUri, physicalDeviceTypeUri, aspectUri, functionalityUri );
	            	// certLog.debug("{} : getDeviceListByLoc_DeviceType_Aspect_Func from knowledge base :\nlocationUri={}\n physicalDeviceTypeUri={}\n aspectUri={}\n functionalityUri={}", sessionId, locationUri, physicalDeviceTypeUri, aspectUri, functionalityUri );
	            	
	            	certLog.debug("=============START(CVO_TYPE_DEVICETYPE)==============");
	            	certLog.debug("sessionID : " + sessionId);
	            	certLog.debug("===============SDA Request Parameters================");
	            	certLog.debug("1. locationUri : " + locationUri);
	            	certLog.debug("2. physicalDeviceTypeUri : " + physicalDeviceTypeUri);
	            	certLog.debug("3. aspectUri : " + aspectUri);
	            	certLog.debug("4. functionalityUri : " + functionalityUri);
	            	certLog.debug("===============SDA Response DeviceList================");
	            	certLog.debug("deviceList : " + deviceList.toString());
	            	certLog.debug("=======================END============================");
			       	
		        } else  if (cvoType.equals("CVO_TYPE_ASPECT")) {
			       	//DeviceId 목록을 가져옴
			       	deviceList = new SdaManager().getDeviceListByLoc_Aspect_Func(locationUri, aspectUri, functionalityUri);
	            	log.info("getDeviceListByLoc_Aspect_Func :\nlocationUri={}\n aspectUri={}\n functionalityUri={}", locationUri, aspectUri, functionalityUri );
	            	// certLog.debug("{} : getDeviceListByLoc_Aspect_Func :\nlocationUri={}\n aspectUri={}\n functionalityUri={}", sessionId, locationUri, aspectUri, functionalityUri );
	            	
	            	certLog.debug("===============START(CVO_TYPE_ASPECT)================");
	            	certLog.debug("sessionID : " + sessionId);
	            	certLog.debug("===============SDA Request Parameters================");
	            	certLog.debug("1. locationUri : " + locationUri);
	            	certLog.debug("2. aspectUri : " + aspectUri);
	            	certLog.debug("3. functionalityUri : " + functionalityUri);
	            	certLog.debug("===============SDA Response DeviceList================");
	            	certLog.debug("deviceList : " + deviceList.toString());
	            	certLog.debug("========================END===========================");

		        } else  if (cvoType.equals("CVO_TYPE_NONEDEVICE")) {
		        } 
	        }    
	        if (deviceList==null) {
	        	log.error("deviceList==null");
	        	return;
	        }
	        //4) Device ID 타입 CVO 생성
	        ArrayList<String> cvoIdList = new ArrayList<>();
	        //cvoIdList.add(cvoBaseId);
	        
	        for (String deviceId:deviceList) {
	            String builtCvoId = /*cvoBaseId + "-" +*/ deviceId.substring(deviceId.lastIndexOf("/")+1,deviceId.length()) + "-CVO";
	            
	        	AGenericCompositeVirtualObject builtCvo = new DefaultCompositeVirtualObject();
	       		builtCvo.setDeviceId(deviceId);              //DeviceId
	       		builtCvo.setLocationId(locationId);          //locationId
	       		builtCvo.setCvoType("CVO_TYPE_DEVICEID");    //cvoType
	       		builtCvo.setBaseCvoId(cvoBaseId);			 //cvoBase
	       		builtCvo.setVirtualObjectList(commonVoList); //vo
	       		builtCvo.setId(ruleBodyId);					 
	       		builtCvoList.add(builtCvo);
	       		
	       		cvoIdList.add(builtCvoId);
	       	}

	        if (cvoType.equals("CVO_TYPE_DEVICEID")) {
	        	//VO list
	            handleVirtualObjectList(builtCvoList.get(0), compositeVirtualObject.getStateStore());
	
	        } else {

	        	//String sessionId = getTracking().getSessionId();
	        	SessionEntity sessionCvo = databaseManager.getSessionData(sessionId);

	        	String cvoIds=null;
	        	String prevServiceKey = sessionCvo.getServiceKey();
	        	if (prevServiceKey==null)
	        		cvoIds = cvoIdList.toString();
	        	else
	        		cvoIds = prevServiceKey +","+ cvoIdList.toString();
	        	
	            //session.setId(getTracking().getSessionId());
	            sessionCvo.setServiceKey(cvoIds);
	            databaseManager.updateSessionData(sessionCvo);

	            handleCompositeVirtualObjectList(builtCvoList, compositeVirtualObject.getStateStore());
	        }
       	}
        else { 
        	//VO 없는 경우 완료
        	SessionEntity session = new SessionEntity();
            session.setId(getTracking().getSessionId());
            session.setServiceResult("Happen"); //Session Data는 완료 처리
            databaseManager.updateSessionData(session);
        }
    }


    /**
     * VO list process
     * VirtualObject list 처리
     * @param list VO list
     */
    private void handleVirtualObjectList(IGenericCompositeVirtualObject compositeVirtualObject, IGenericStateStore<?, ?> stateStore) {

    	List<IGenericVirtualObject> list = compositeVirtualObject.getVirtualObjectList();
    	
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        String deviceId = compositeVirtualObject.getDeviceId();
        
        if (list != null) {
            for (IGenericVirtualObject virtualObject : list) {
                // tracking
                getTracking().setProcessId(virtualObject.getId());
                getTracking().setProcessName(virtualObject.getName());
                TrackingProducer.send(getTracking());
    
                // grib session location
	            String voId = deviceId.substring(deviceId.lastIndexOf("/")+1,deviceId.length()) + "-"+virtualObject.getId();

                SessionEntity sessionVo = new SessionEntity();
                sessionVo.setId(getTracking().getSessionId());
                //session.setVirtualobjectKey(virtualObject.getId());
                sessionVo.setVirtualobjectKey(voId);
                databaseManager.createSessionDataVo(sessionVo);
                log.trace("session vo : {}", sessionVo);
    
                SessionEntity session = new SessionEntity();
                session.setId(getTracking().getSessionId());
                session.setVirtualobjectResult("CONTROL_EXECUTION");
                databaseManager.updateSessionData(session);

                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, virtualObject);
                
                AGenericVirtualObject vo = (AGenericVirtualObject)virtualObject;
                vo.setDeviceId(deviceId);
                
                // vo 각각에 대한 처리
                handleVirtualObject(virtualObject);
            }
        }
    }

    /**
     * VO process
     * vo 각각에 대한 처리
     * @param virtualObject virtual object
     */
    private void handleVirtualObject(IGenericVirtualObject virtualObject) {
        if (virtualObject != null) {
            // virtual object biz.
            //..
            //publish a virtual object
            publishVirtualObject(virtualObject);
        }
    }

    /**
     * publish a VirtualObject.<BR/>
     * virtualObject 클래스를 mq 클래스로 변환
     * @param virtualObject IGenericVirtualObject
     */
    private void publishVirtualObject(IGenericVirtualObject virtualObject) {
        //generate a VirtualObjectForMQ model
        VirtualObjectForMQ virtualObjectForMQ = ModelMapper.toVirtualObjectForMQ(virtualObject);
        virtualObjectForMQ.setTrackingEntity(getTracking());
        //generate to string.
        String jsonString = toJsonString(virtualObjectForMQ);
        //publish by producer
        publishToVoMq(jsonString);
    }
    
    /**
     * VirtualObjectForMQ to json String.<BR/>
     * virtualobject mq 클래스를 큐로 전송하기 위해 json 으로 변환
     * @param virtualObjectForMQ VirtualObjectForMQ
     * @return json String
     */
    private String toJsonString(VirtualObjectForMQ virtualObjectForMQ) {
        return ModelMapper.writeJsonString(virtualObjectForMQ);
    }

    /**
     * publishToMq a data.<BR/>
     * virtualobject 큐로 전송
     * @param data data
     * @return result
     */
    private Future<RecordMetadata> publishToVoMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, Settings2.TOPIC_VIRTUAL_OBJECT);
        Future<RecordMetadata> result = producerHandler.send(data);
        producerHandler.close();
        return result;
    }


    /**
     * CVO list process
     * cvo 목록에 대한 처리
     * @param list CVO list
     */
    private void handleCompositeVirtualObjectList(List<IGenericCompositeVirtualObject> list, IGenericStateStore<?, ?> stateStore) {
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());
    
        //List<IGenericVirtualObject> virtualObjectList = null;
        
        if (list != null && list.size() > 0) {
            for (IGenericCompositeVirtualObject cvo : list) {
                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, cvo);

                getTracking().setProcessId(cvo.getId());
                getTracking().setProcessName(cvo.getName());
                TrackingProducer.send(getTracking());
    
                // cvo 각각에 대한 처리
                handleCompositeVirtualObject(cvo);
            }
        }
    }

    /**
     * CVO process
     * cvo 각각에 대한 처리
     * @param cvo composite virtual object
     */
    private void handleCompositeVirtualObject(IGenericCompositeVirtualObject cvo) {
        // composite virtual object biz.
        log.debug("cvo={}, basecvo={}", cvo.getId(), cvo.getBaseCvoId());
        
        //handleVirtualObjectList(cvo.getVirtualObjectList(), cvo.getStateStore());
        publishCompositeVirtualObject(cvo);
    }

    /**
     * publish a CompositeVirtualObject.<BR/>
     * CompositeVirtualObject 클래스를 mq 클래스로 변환
     * @param cvo IGenericCompositeVirtualObject
     */
    private void publishCompositeVirtualObject(IGenericCompositeVirtualObject cvo) {
        //generate a VirtualObjectForMQ model
    	CompositeVirtualObjectForMQ compositeVirtualObjectForMQ = ModelMapper.toCompositeVirtualObjectForMQ(cvo);
        compositeVirtualObjectForMQ.setTrackingEntity(getTracking());
        //generate to string.
        String jsonString = toJsonString(compositeVirtualObjectForMQ);
        //publish by producer
        publishToMq(jsonString);
    }

    /**
     * CompositeVirtualObjectForMQ to json String.<BR/>
     * compositeVirtualObjectForMQ mq 클래스를 큐로 전송하기 위해 json 으로 변환
     * @param compositeVirtualObjectForMQ CompositeVirtualObjectForMQ
     * @return json String
     */
    private String toJsonString(CompositeVirtualObjectForMQ compositeVirtualObjectForMQ) {
        return ModelMapper.writeJsonString(compositeVirtualObjectForMQ);
    }

    /**
     * publishToMq a data.<BR/>
     * compositevirtualobject 큐로 전송
     * @param data data
     * @return result
     */
    private Future<RecordMetadata> publishToMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, Settings2.TOPIC_COMPOSITE_VIRTUAL_OBJECT);
        Future<RecordMetadata> result = producerHandler.send(data);
        producerHandler.close();
        return result;
    }
}
