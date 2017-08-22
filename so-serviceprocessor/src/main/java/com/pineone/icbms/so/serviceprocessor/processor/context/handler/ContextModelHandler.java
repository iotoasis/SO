package com.pineone.icbms.so.serviceprocessor.processor.context.handler;

import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.messagequeue.model.OrchestrationServiceForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;

import java.util.List;

/**
 * ContextModel handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 20..
 */
public class ContextModelHandler extends AProcessHandler<IGenericContextModel> {
    /**
     * constructor
     *
     * @param databaseManager DatabaseManager
     */
    public ContextModelHandler(IDatabaseManager databaseManager) {
        super(databaseManager);
    }

    /**
     * handle a message.<BR/>
     *
     * @param contextModel handle message
     */
    @Override
    public void handle(IGenericContextModel contextModel) {
        List<IGenericLocation> locationList = (List<IGenericLocation>) (contextModel.getState(Const.LOCATION_URI_LIST));
        handle(contextModel, locationList);
    }

    /**
     * ContextModel handle.<BR/>
     *
     * @param contextModel ContextModel
     */
    public void handle(IGenericContextModel contextModel, List<IGenericLocation> locationList) {

        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        if (contextModel != null) {
            //1 profile with many location
            if (locationList != null && locationList.size() > 0) {
                for (IGenericLocation location : locationList) {
                    List<ProfileForDB> profileForDbList = databaseManager.getProfileListByContextModelSidAndLocationUri(contextModel.getId(), location.getUri());
                    if (profileForDbList != null && profileForDbList.size() > 0) {
                        List<IGenericProfile> profileList = ModelMapper.toProfileList(profileForDbList);
                        //get a OrchestrationService by orchestrationservice id in profile
                        for (IGenericProfile profile : profileList) {
                            getTracking().setProcessId(profile.getId());
                            getTracking().setProcessName(profile.getName());
                            TrackingProducer.send(getTracking()
//                                    , getClass().getSimpleName(), profile.getId(), profile.getName()
//                                    , new Object(){}.getClass().getEnclosingMethod().getName()
                            );

                            // profile handler
                            profileHandle(profile);
                        }
                    }
                    // When Profile list NOT exist,
                    else {
                        log.info("A profile NOT exist for contextmodel id, location uri: {}, {}", contextModel.getId(), location.getUri());
                        getTracking().setProcessId("profile 없음");
                        getTracking().setProcessName("");
                        TrackingProducer.send(getTracking()
//                                , getClass().getSimpleName(), "profile 없음"
//                                , new Object(){}.getClass().getEnclosingMethod().getName()
                        );
                    }
                }
            } else {
                log.warn("Location list NOT exist for contextmodel id: {}", contextModel.getId());
                getTracking().setProcessId("profile 없음");
                getTracking().setProcessName("");
                TrackingProducer.send(getTracking()
//                        getTracking(), getClass().getSimpleName(), "Location list NOT exist"
//                        , new Object(){}.getClass().getEnclosingMethod().getName()
                );
            }
        } else {
            log.warn("The contextmodel is NULL.");
            getTracking().setProcessId("contextmodel 없음");
            getTracking().setProcessName("");
            TrackingProducer.send(getTracking()
//                    getTracking(), getClass().getSimpleName(), "contextmodel 없음"
//                    , new Object(){}.getClass().getEnclosingMethod().getName()
            );
        }
    }

    /**
     * Profile handle.<BR/>
     *
     * @param profile Profile
     */
    public void profileHandle(IGenericProfile profile) {
        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        IGenericOrchestrationService orchestrationService = profile.getOrchestrationService();
        if (orchestrationService != null) {
            IGenericLocation location = profile.getLocation();
            //TODO: if (locatino NOT exist)
            //..
            String contextModelId = profile.getContextModel().getId();
            //TODO: if (context model NOT exist)
            //..
            //TODO: biz implements
            //TODO: fixed device discovery?
            //..

            //publish a orchestration service
            publishOrchestrationService(orchestrationService, location.getUri(), contextModelId);
        }
    }

    /**
     * publish a OrchestrationService to MQ.<BR/>
     *
     * @param orchestrationService IGenericOrchestrationService
     */
    private void publishOrchestrationService(IGenericOrchestrationService orchestrationService, String locationUri, String contextModelId) {
        //generate a OrchestrationServiceForMQ model
        OrchestrationServiceForMQ orchestrationServiceForMQ = ModelMapper.toOrchestrationServiceForMQ(orchestrationService);

        orchestrationServiceForMQ.setTrackingEntity(getTracking());

        //TODO: location id =? location uri
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
     *
     * @param data data
     * @return result
     */
    public void publishToMq(String data) {
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "orchestrationservice");
        producerHandler.send(data);
        producerHandler.close();
    }
}

