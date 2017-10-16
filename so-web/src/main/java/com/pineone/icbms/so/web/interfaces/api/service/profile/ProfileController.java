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
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
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

    //스케쥴러에 의한 profile처리는 context로그에 넣음
    final String contextClsName = "com.pineone.icbms.so.serviceprocessor.processor.context.ProfileController";
    protected Logger contextLog = LoggerFactory.getLogger(contextClsName);

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
        
        if (profileTransFormData==null) {
        	contextLog.warn("Input Profile is null");
        	return null;
        }
        String inputProfileId = profileTransFormData.getId();
    	contextLog.debug("Checking CM by schedule : input profile=[{}]", inputProfileId);

    	ProfileForDB profileForDb = databaseManager.getProfileById(profileTransFormData.getId());
        IGenericProfile profile = ModelMapper.toProfile(profileForDb);
        
        SessionEntity sessionEntity = new SessionEntity();
        
        if (profile==null) {
        	contextLog.warn("Profile is null");
        	return null;
        }
        
        IGenericContextModel contextModel = profile.getContextModel();
        if (contextModel == null) {
        	contextLog.warn( "contextModel is null");
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
        sessionEntity.setPriorityKey(priority);
        //contextLog.debug("session : {}", sessionEntity);

        // grib session profile
        //SessionEntity sessionProfile = new SessionEntity();
        //sessionEntity.setId(sessionId);
        sessionEntity.setProfileKey(profile.getId());
        sessionEntity.setProfileName(profile.getName());
        //contextLog.debug("session profile : {}", sessionEntity);

        databaseManager.createSessionData(sessionEntity);
        
        boolean isCmProceed = false; //CM 처리되었나?
        
        //SDA로 부터 CM발생 여부 체크
        List<String> locationList = new SdaManager().retrieveEventLocationList(contextModelId);

        contextLog.debug("called SDA: cm={}, location={}", contextModelId, locationList.toString());

        if (locationList != null && locationList.size() > 0) {

            for (String location : locationList) {
                if (location.equals(profile.getLocation().getUri())) {
                	//scheduler 또는 에 의한 Profile 내 OS 구동임을 남겨야 함

                	ContextModelHandler contextModelHandler = new ContextModelHandler(databaseManager);
                	contextModelHandler.setTracking(trackingEntity);
                	contextModelHandler.profileHandle(profile);
                	isCmProceed = true;

                    //sessionEntity.setContextmodelResult("Happen");
                    //databaseManager.updateSessionData(sessionEntity);
                	
                    // grib session location
                    SessionEntity sessionLocation = new SessionEntity();
                    sessionLocation.setId(sessionId);
                    sessionLocation.setLocationId(location);
                    databaseManager.createSessionDataLocation(sessionLocation);
    
                    contextLog.warn("O: result: Happen cm=[{}], Location={} , sessionId={}", contextModelId, location, sessionId);
                    //contextLog.debug("session location : {}", sessionLocation);
                }
            }
        }

        //Location이 없거나 처리되지 않았을때
        if (isCmProceed == false) {
        	contextLog.warn("X: result: Not happened cm=[{}]", contextModelId);

            sessionEntity.setId(sessionId);
            sessionEntity.setContextmodelResult("NotHappen");
            databaseManager.updateSessionData(sessionEntity);

        	// grib session location
            SessionEntity sessionLocation = new SessionEntity();
            sessionLocation.setId(sessionId);
            sessionLocation.setLocationId("");
            databaseManager.createSessionDataLocation(sessionLocation);
            
            //contextLog.debug("session location : {}", sessionLocation);
        }
        return profile;
    }

    /**
     * response for request "/profile/force, HTTP-method:POST".<BR/>
     * /force url에 의해 발생됨
     *
     * @param profileTransFormData profile
     * @return profile
     */
    @RequestMapping(value="/force", method = RequestMethod.POST)
    public IGenericProfile forceProfile(@RequestBody ProfileTransFormData profileTransFormData, HttpServletRequest request) {
    	if (profileTransFormData==null) {
    		return null;
    	}
        String inputProfileId = profileTransFormData.getId();
    	contextLog.debug("Force Profile by URL /profile/force : profile={}", inputProfileId);

    	ProfileForDB profileForDb = databaseManager.getProfileById(profileTransFormData.getId());
        IGenericProfile profile = ModelMapper.toProfile(profileForDb);
        
        IGenericLocation location = profile.getLocation();
        if(location != null) {
            //TODO: scheduler 또는 에 의한 Profile 내 OS 구동임을 남겨야 함
            TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
            ContextModelHandler contextModelHandler = new ContextModelHandler(databaseManager);
            contextModelHandler.setTracking(trackingEntity);
            contextModelHandler.profileHandle(profile);

            contextLog.warn("O: result: called profile={}, Location={} ", inputProfileId, location.getId());
        } else {
        	contextLog.warn("X: result: No location profile={}", inputProfileId);
        }
        return profile;
    }
}
