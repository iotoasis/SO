package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.handler;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.VirtualObjectForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.serviceutil.state.StateStoreUtil;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;
import com.pineone.icbms.so.virtualobject.composite.IGenericCompositeVirtualObject;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.virtualobject.state.IGenericStateStore;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.concurrent.Future;

/**
 * OrchestrationService handler.<BR/>
 * 서비스 핸들러 : os 와 연결된 cvo 와 vo를 셋팅하고 virtualobject 큐로 메시지를 전송한다.
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

        getTracking().setProcess(getClass().getSimpleName());
    
        // grib session location
        SessionEntity session = new SessionEntity();
        session.setId(getTracking().getSessionId());
        session.setServiceKey(orchestrationService.getId());
        session.setServicemodelKey(orchestrationService.getId());
        session.setServicemodelName(orchestrationService.getName());
        log.debug("session service : {}", session);
        databaseManager.updateSessionData(session);
    
        session = new SessionEntity();
        session.setId(getTracking().getSessionId());
        session.setServicemodelResult("CONTROL_EXECUTION");
        session.setServiceResult("CONTROL_EXECUTION");
        databaseManager.updateSessionData(session);

        // OS list : os 를 복수로 확장하게 될 경우 사용
//        if (orchestrationService.getOrchestrationServiceList() != null) {
//            handleOrchestrationServiceList(orchestrationService.getOrchestrationServiceList()
//                    , orchestrationService.getStateStore());
//        }
        //CVO list
        if (orchestrationService.getCompositeVirtualObjectList() != null) {
            handleCompositeVirtualObjectList(orchestrationService.getCompositeVirtualObjectList()
                    , orchestrationService.getStateStore());
        }
        // cvo 목록이 없는 경우 vo로 지정되어 있는지 확인한다.
        if (orchestrationService.getCompositeVirtualObjectList() == null
                || orchestrationService.getCompositeVirtualObjectList().size() == 0) {
            //VO list
            if (orchestrationService.getVirtualObjectList() != null) {
                handleVirtualObjectList(orchestrationService.getVirtualObjectList()
                        , orchestrationService.getStateStore());
            }
        }
    }


    /**
     * OS list process
     *
     * @param list OS list
     */
    private void handleOrchestrationServiceList(List<IGenericOrchestrationService> list, IGenericStateStore stateStore) {
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
        log.warn("getCompositeVirtualObjectListByOrchestrationId : {}", os.getId());
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
    private void handleCompositeVirtualObjectList(List<IGenericCompositeVirtualObject> list, IGenericStateStore stateStore) {
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
        //
        handleVirtualObjectList(cvo.getVirtualObjectList(), cvo.getStateStore());
    }

    /**
     * VO list process
     * VirtualObject list 처리
     * @param list VO list
     */
    private void handleVirtualObjectList(List<IGenericVirtualObject> list, IGenericStateStore stateStore) {
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        if (list != null) {
            for (IGenericVirtualObject virtualObject : list) {
                // tracking
                getTracking().setProcessId(virtualObject.getId());
                getTracking().setProcessName(virtualObject.getName());
                TrackingProducer.send(getTracking());
    
                // grib session location
                SessionEntity session = new SessionEntity();
                session.setId(getTracking().getSessionId());
                session.setVirtualobjectKey(virtualObject.getId());
                log.debug("session vo : {}", session);
                databaseManager.createSessionDataVo(session);
    
                session = new SessionEntity();
                session.setId(getTracking().getSessionId());
                session.setVirtualobjectResult("CONTROL_EXECUTION");
                databaseManager.updateSessionData(session);

                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, virtualObject);
                
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
        publishToMq(jsonString);
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
    private Future<RecordMetadata> publishToMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "virtualobject");
        Future<RecordMetadata> result = producerHandler.send(data);
        producerHandler.close();
        return result;
    }
}
