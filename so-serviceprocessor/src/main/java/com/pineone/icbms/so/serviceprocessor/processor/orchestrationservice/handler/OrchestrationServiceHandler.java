package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.handler;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.RuleBodyForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.CompositeVirtualObjectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.composite.DefaultCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * OrchestrationService handler.<BR/>
 * 서비스 핸들러 : os 와 연결된 cvo를 셋팅하고 compositevirtualobject 큐로 메시지를 전송한다.
 * Created by uni4love on 2017. 1. 20..
 */
public class OrchestrationServiceHandler extends AProcessHandler<IGenericOrchestrationService> {

    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public OrchestrationServiceHandler(IDatabaseManager databaseManager) {
        super(databaseManager);
    }

    /**
     * OrchestrationService handler.<BR/>
     *
     * @param orchestrationService IGenericOrchestrationService
     */
    public void handle(IGenericOrchestrationService orchestrationService) {

    	String osId, osName, osResult;
    	
        getTracking().setProcess(getClass().getSimpleName());
    
        if (orchestrationService != null) {
        	osId = orchestrationService.getId(); 
        	osName = orchestrationService.getName(); 
        	osResult = "CONTROL_EXECUTION";
        } else {
        	osId = null; 
        	osName = null; 
        	osResult = "CONTROL_IGNORE";
        }

        // grib session
        SessionEntity sessionOs = new SessionEntity();
        sessionOs.setId(getTracking().getSessionId());
        sessionOs.setServicemodelKey(osId);
        sessionOs.setServicemodelName(osName);
        sessionOs.setServicemodelResult(osResult);
        databaseManager.updateSessionData(sessionOs);

        log.trace("session service : {}", sessionOs);

        // OS list : os 를 복수로 확장하게 될 경우 사용
//        if (orchestrationService.getOrchestrationServiceList() != null) {
//            handleOrchestrationServiceList(orchestrationService.getOrchestrationServiceList()
//                    , orchestrationService.getStateStore());
//        }

        // os id 로 Rule body 목록을 조회한다
        log.debug("getRuleBodyListByOsId : {}", osId);
        List<RuleBodyForDB> ruleBodyForDBList = databaseManager.getRuleBodyListByOsId(osId);

        //convert to List<IGenericCompositeVirtualObject>
        List<IGenericCompositeVirtualObject> cvoList = new ArrayList<>();
        for (RuleBodyForDB rubleBodyItem:ruleBodyForDBList) {
        	DefaultCompositeVirtualObject compositeVirtualObject = new DefaultCompositeVirtualObject();
            compositeVirtualObject.setId(rubleBodyItem.getId());

            //cvo_type, physical_device_type_id, device_id
            compositeVirtualObject.setCvoType(rubleBodyItem.getCvoType());
            compositeVirtualObject.setPhysicalDeviceTypeId(rubleBodyItem.getPhysicalDeviceTypeId());
            compositeVirtualObject.setDeviceId(rubleBodyItem.getDeviceId());
        	
            //base_cvo_id, location_id
            compositeVirtualObject.setBaseCvoId(rubleBodyItem.getBaseCvoId());
            compositeVirtualObject.setLocationId(rubleBodyItem.getLocationId());

            compositeVirtualObject.setOsId(rubleBodyItem.getOsId());

        	cvoList.add(compositeVirtualObject);
        }

        
       if (cvoList != null && cvoList.size()>0) {
        	ArrayList<String> cvoIdList= new ArrayList<>();
        	for (int i=0; i< cvoList.size();i++) {
        		IGenericCompositeVirtualObject cvo = cvoList.get(i);
        		//cvoIdList.add(cvo.getId());
        		cvoIdList.add(cvo.getBaseCvoId());
        	}
        	String cvoIds= cvoIdList.toString();
        	String cvoResult = "CONTROL_EXECUTION";
        	
        	sessionOs = new SessionEntity();
            sessionOs.setId(getTracking().getSessionId());
            sessionOs.setServiceKey(cvoIds);
            sessionOs.setServiceResult(cvoResult);
            databaseManager.updateSessionData(sessionOs);

            
        	handleCompositeVirtualObjectList(cvoList, orchestrationService.getStateStore());
        } else {
        	// cvo 목록이 없는 경우 vo로 지정되어 있는지 확인한다.
        	
        	SessionEntity sessionCm = new SessionEntity();
            sessionCm.setId(getTracking().getSessionId());
            sessionCm.setContextmodelResult("Happen"); //Session Data는 완료 처리
            databaseManager.updateSessionData(sessionCm);
        }
    }


    /**
     * OS list process
     *
     * @param list OS list
     */
    private void handleOrchestrationServiceList(List<IGenericOrchestrationService> list, IGenericStateStore<?, ?> stateStore) {
        if (list != null) {
            for (IGenericOrchestrationService os : list) {
                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, os);

                handleOrchestrationService(os);
            }
        }
    }

    /**
     * OS process.<BR/>
     *
     * @param os             IGenericOrchestrationService
     */
    private void handleOrchestrationService(IGenericOrchestrationService os) {
        // orchestration service biz.
        //..
        //OS list
        handleOrchestrationServiceList(os.getOrchestrationServiceList(), os.getStateStore());
        
        //CVO list
        // os id 로 cvo 목록을 조회한다
        log.debug("getCompositeVirtualObjectListByOrchestrationId : {}", os.getId());
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList =
                databaseManager.getCompositeVirtualObjectListByOrchestrationId(os.getId());

        //convert to List<IGenericCompositeVirtualObject>
        List<IGenericCompositeVirtualObject> compositeVirtualObjectList =
                ModelMapper.toCompositeVirtualObjectList(compositeVirtualObjectForDBList);

        //in CompositeVirtualObject
        handleCompositeVirtualObjectList(compositeVirtualObjectList, os.getStateStore());

//        //VO list
//        handleVirtualObjectList(os.getVirtualObjectList(), os.getStateStore());
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
        log.debug("cvo : {}", cvo.getId());
        
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
