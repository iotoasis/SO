package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.handler;

import com.pineone.icbms.so.interfaces.database.model.CompositeVirtualObjectForDB;
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
 * <p>
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

        //OS list
        if (orchestrationService.getOrchestrationServiceList() != null) {
            handleOrchestrationServiceList(orchestrationService.getOrchestrationServiceList()
                    , orchestrationService.getStateStore());
        }
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
        //TODO: orchestration service biz.
        //..
        //OS list
        handleOrchestrationServiceList(os.getOrchestrationServiceList(), os.getStateStore());
        //CVO list
        //retreive VO list from db
        List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList =
                databaseManager.getCompositeVirtualObjectListByOrchestrationId(os.getId());
        //convert to List<IGenericCompositeVirtualObject>
        List<IGenericCompositeVirtualObject> compositeVirtualObjectList =
                ModelMapper.toCompositeVirtualObjectList(compositeVirtualObjectForDBList);
        //in CompositeVirtualObject
        handleCompositeVirtualObjectList(compositeVirtualObjectList, os.getStateStore());
        //VO list
        handleVirtualObjectList(os.getVirtualObjectList(), os.getStateStore());
    }

    /**
     * CVO list process
     *
     * @param list CVO list
     */
    private void handleCompositeVirtualObjectList(List<IGenericCompositeVirtualObject> list, IGenericStateStore stateStore) {
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        if (list != null && list.size() > 0) {
            for (IGenericCompositeVirtualObject cvo : list) {
                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, cvo);

                getTracking().setProcessId(cvo.getId());
                getTracking().setProcessName(cvo.getName());
                TrackingProducer.send(getTracking()
//                        , getClass().getSimpleName(), cvo.getId(), cvo.getName()
//                        , new Object(){}.getClass().getEnclosingMethod().getName()
                );

                handleCompositeVirtualObject(cvo);
            }
        }
    }

    /**
     * CVO process
     *
     * @param cvo composite virtual object
     */
    private void handleCompositeVirtualObject(IGenericCompositeVirtualObject cvo) {
        //TODO: composite virtual object biz.
        //..
        handleVirtualObjectList(cvo.getVirtualObjectList(), cvo.getStateStore());
    }

    /**
     * VO list process
     *
     * @param list VO list
     */
    private void handleVirtualObjectList(List<IGenericVirtualObject> list, IGenericStateStore stateStore) {
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        if (list != null) {
            for (IGenericVirtualObject virtualObject : list) {
                // TODO tracking
                getTracking().setProcessId(virtualObject.getId());
                getTracking().setProcessName(virtualObject.getName());
                TrackingProducer.send(getTracking()
//                        , getClass().getSimpleName(), virtualObject.getId(), virtualObject.getName()
//                        , new Object(){}.getClass().getEnclosingMethod().getName()
                );

                //TODO: refactor copying StateStore
                StateStoreUtil.copyStateStore(stateStore, virtualObject);
                handleVirtualObject(virtualObject);
            }
        }
    }

    /**
     * VO process
     *
     * @param virtualObject virtual object
     */
    private void handleVirtualObject(IGenericVirtualObject virtualObject) {
        if (virtualObject != null) {
            //TODO: virtual object biz.
            //..
            //publish a virtual object
            publishVirtualObject(virtualObject);
        }
    }

    /**
     * publish a VirtualObject.<BR/>
     *
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
     *
     * @param virtualObjectForMQ VirtualObjectForMQ
     * @return json String
     */
    private String toJsonString(VirtualObjectForMQ virtualObjectForMQ) {
        return ModelMapper.writeJsonString(virtualObjectForMQ);
    }

    /**
     * publishToMq a data.<BR/>
     *
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
