package com.pineone.icbms.so.web.interfaces.api.service.profile;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.serviceprocessor.processor.context.handler.ContextModelHandler;
import com.pineone.icbms.so.serviceprocessor.repository.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * context for ContextModel<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 18..
 */
@Before(@BeforeElement(BeforeTtrackingHandler.class))
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service/profile")
public class ProfileController {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DatabaseManager databaseManager;

    /**
     * response for request "/profile, HTTP-method:POST".<BR/>
     * scheduler가 특정 주기로 호출됨
     *
     * @param profileTransFormData profile
     * @return profile
     */
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public IGenericProfile callFromScheduler(@RequestBody ProfileTransFormData profileTransFormData, HttpServletRequest request) {
        log.debug("input:profile: {}", profileTransFormData);
        
        ProfileForDB profileForDb = databaseManager.getProfileById(profileTransFormData.getId());
        IGenericProfile profile = ModelMapper.toProfile(profileForDb);
        
        SessionEntity sessionEntity = new SessionEntity();
        
        if (profile==null) {
            log.warn("Profile is null");
        	return null;
        }
        
        IGenericContextModel contextModel = profile.getContextModel();
        if (contextModel == null) {
            log.warn( "contextModel is null");
        	return null;
        }

        String contextModelId = contextModel.getId();
        ContextModelForDB cm = databaseManager.getContextModelById(contextModelId);
        String contextModelName = cm.getName();
        
        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
        String sessionId = trackingEntity.getSessionId();
        String priority = profileForDb.getPriority();
        
        // grib session
        sessionEntity.setId(sessionId);
        sessionEntity.setContextmodelKey(contextModelId);
        sessionEntity.setContextmodelName(contextModelName);
        sessionEntity.setContextmodelResult("NotHappen");
        //sessionEntity.setPriorityKey("LOW");
        sessionEntity.setPriorityKey(priority);
        log.debug("session : {}", sessionEntity);

        // grib session profile
        //SessionEntity sessionProfile = new SessionEntity();
        //sessionEntity.setId(sessionId);
        sessionEntity.setProfileKey(profile.getId());
        sessionEntity.setProfileName(profile.getName());
        log.debug("session profile : {}", sessionEntity);
        //databaseManager.updateSessionData(sessionEntity);

        databaseManager.createSessionData(sessionEntity);
            
        List<String> locationList = new SdaManager().retrieveEventLocationList(profile.getContextModel().getId());
        if (locationList != null && locationList.size() > 0) {

            for (String location : locationList) {
                if (location.equals(profile.getLocation().getUri())) {
                	//scheduler 또는 에 의한 Profile 내 OS 구동임을 남겨야 함

                	ContextModelHandler contextModelHandler = new ContextModelHandler(databaseManager);
                	contextModelHandler.setTracking(trackingEntity);
                	contextModelHandler.profileHandle(profile);

                    sessionEntity.setContextmodelResult("Happen");
                    databaseManager.updateSessionData(sessionEntity);
                	
                    // grib session location
                    SessionEntity sessionLocation = new SessionEntity();
                    sessionLocation.setId(sessionId);
                    sessionLocation.setLocationId(location);
                    log.debug("session location : {}", sessionLocation);
                    databaseManager.createSessionDataLocation(sessionLocation);
    
                    sessionLocation = new SessionEntity();
                    sessionLocation.setDeviceLocation(location);
                    databaseManager.updateSessionData(sessionLocation);
                }
            }
        } else {
            log.warn("The profile does NOT have a location.");

            // grib session location
            SessionEntity sessionLocation = new SessionEntity();
            sessionLocation.setId(sessionId);
            sessionLocation.setLocationId("");
            log.debug("session location : {}", sessionLocation);
            databaseManager.createSessionDataLocation(sessionLocation);
            
        }
        return profile;
    }

    /**
     * response for request "/profile/force, HTTP-method:POST".<BR/>
     * scheduler가 특정 주기로 호출됨
     *
     * @param profileTransFormData profile
     * @return profile
     */
    @RequestMapping(value="/force", method = RequestMethod.POST)
    public IGenericProfile forceProfile(@RequestBody ProfileTransFormData profileTransFormData, HttpServletRequest request) {
        log.debug("input:profile: {}", profileTransFormData);
        ProfileForDB profileForDb = databaseManager.getProfileById(profileTransFormData.getId());
        IGenericProfile profile = ModelMapper.toProfile(profileForDb);
        if(profile.getLocation() != null) {
            //TODO: scheduler 또는 에 의한 Profile 내 OS 구동임을 남겨야 함
            TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
            ContextModelHandler contextModelHandler = new ContextModelHandler(databaseManager);
            contextModelHandler.setTracking(trackingEntity);
            contextModelHandler.profileHandle(profile);
        } else {
            log.warn("The profile does NOT have a location.");
        }
        return profile;
    }
}
