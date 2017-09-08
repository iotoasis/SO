package com.pineone.icbms.so.serviceprocessor.processor.context.handler;

import com.pineone.icbms.so.interfaces.database.dao.SessionDao;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.OrchestrationServiceForMQ;
import com.pineone.icbms.so.interfaces.messagequeue.producer.tracking.TrackingProducer;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceprocessor.processor.AProcessHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.conversion.DataConversion;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.util.priority.Priority;
import com.pineone.icbms.so.util.session.DefaultSession;
import com.pineone.icbms.so.util.session.Session;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.orchestrationservice.IGenericOrchestrationService;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ContextModel handler.<BR/>
 * 상황모델에 대한 처리 로직
 * Created by uni4love on 2017. 1. 20..
 */
public class ContextModelHandler extends AProcessHandler<IGenericContextModel> {

//    @Autowired
//    SessionDao sessionDao;
    //SessionStore sessionStore;

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
     * 상황모델에 대한 처리
     * @param contextModel ContextModel
     */
    public void handle(IGenericContextModel contextModel, List<IGenericLocation> locationList) {
        //
        String sessionId = getTracking().getSessionId();
        //contextModel.setSessionId(sessionId);

        getTracking().setProcess(getClass().getSimpleName());
        getTracking().setProcessMethod(new Object(){}.getClass().getEnclosingMethod().getName());

        getTracking().setProcessId("CM:" + contextModel.getId());
        getTracking().setProcessName(contextModel.getName());
        TrackingProducer.send(getTracking());

        // grib session
//        setSession(IGenericContextModel contextModel, ProfileForDB profileForDB, List<IGenericLocation> locationList)

        if (contextModel != null) {
            // grib session
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setId(sessionId);
            sessionEntity.setContextmodelKey(contextModel.getId());
            sessionEntity.setContextmodelName(contextModel.getName());
            sessionEntity.setContextmodelResult("Happen");
            sessionEntity.setPriorityKey("LOW");
            log.debug("session : {}", sessionEntity);
            databaseManager.createSessionData(sessionEntity);
            
            //1 profile with many location
            if (locationList != null && locationList.size() > 0) {
                // 상황이 발생한 지역에 대한 처리
                for (IGenericLocation location : locationList) {

                    getTracking().setProcessId("LO:" + location.getId());
                    getTracking().setProcessName(location.getName());
                    TrackingProducer.send(getTracking());
    
                    // grib session location
                    SessionEntity sessionLocation = new SessionEntity();
                    sessionLocation.setId(sessionId);
                    sessionLocation.setLocationId(location.getUri());
                    log.debug("session location : {}", sessionLocation);
                    databaseManager.createSessionDataLocation(sessionLocation);
    
                    sessionLocation = new SessionEntity();
                    sessionLocation.setDeviceLocation(location.getUri());
                    databaseManager.updateSessionData(sessionLocation);
    
                    // cm 으로 프로파일 조회
                    log.warn("getProfileListByContextModelSidAndLocationUri : {}, {}", contextModel.getId(), location.getUri());
                    List<ProfileForDB> profileForDbList = databaseManager.getProfileListByContextModelSidAndLocationUri(contextModel.getId(), location.getUri());
                    if (profileForDbList != null && profileForDbList.size() > 0) {
                        List<IGenericProfile> profileList = ModelMapper.toProfileList(profileForDbList);
                        
                        // 프로파일에 대한 처리
                        for (IGenericProfile profile : profileList) {
                            //profile.setSessionId(sessionId);
                            getTracking().setProcessId("PR:" + profile.getId());
                            getTracking().setProcessName(profile.getName());
                            TrackingProducer.send(getTracking());
    
                            // grib session profile
                            SessionEntity sessionProfile = new SessionEntity();
                            sessionProfile.setId(sessionId);
                            sessionProfile.setProfileKey(profile.getId());
                            sessionProfile.setProfileName(profile.getName());
                            log.debug("session profile : {}", sessionProfile);
                            databaseManager.updateSessionData(sessionProfile);

                            // profile handler
                            profileHandle(profile);
                        }
                    }
                    // When Profile list NOT exist,
                    else {
                        log.info("A profile NOT exist for contextmodel id, location uri: {}, {}", contextModel.getId(), location.getUri());
                        getTracking().setProcessId("profile 없음");
                        getTracking().setProcessName("");
                        TrackingProducer.send(getTracking());
    
                        // grib session location
                        SessionEntity session = new SessionEntity();
                        session.setId(sessionId);
                        session.setProfileKey("");
                        session.setProfileName("");
                        log.debug("session : {}", session);
                        databaseManager.updateSessionData(session);
                    }
                }
            } else {
                log.warn("Location list NOT exist for contextmodel id: {}", contextModel.getId());
                getTracking().setProcessId("location 없음");
                getTracking().setProcessName("");
                TrackingProducer.send(getTracking());
    
                // grib session location
                sessionEntity.setProfileKey("");
                sessionEntity.setProfileName("");
                log.debug("session : {}", sessionEntity);
                databaseManager.updateSessionData(sessionEntity);
            }
        } else {
            log.warn("The contextmodel is NULL.");
            getTracking().setProcessId("contextmodel 없음");
            getTracking().setProcessName("");
            TrackingProducer.send(getTracking());
    
            // grib session context
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setId(sessionId);
            sessionEntity.setContextmodelKey(contextModel.getId());
            sessionEntity.setContextmodelName(contextModel.getName());
            sessionEntity.setContextmodelResult("NotHappen");
            log.debug("session : {}", sessionEntity);
            databaseManager.createSessionData(sessionEntity);
        }
    }

    /**
     * Profile handle.<BR/>
     * 각 프로파일을 처리한다
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
    
            // grib session location
//            SessionEntity session = new SessionEntity();
//            session.setId(getTracking().getSessionId());
//            session.setProfileKey("");
//            session.setProfileName("");
//            log.warn("session : {}", session);
//            databaseManager.updateSessionData(session);

            //publish a orchestration service
            publishOrchestrationService(orchestrationService, location.getUri(), contextModelId);
        }
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

