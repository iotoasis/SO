package com.pineone.icbms.so.web.interfaces.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.model.SessionEntity;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.serviceprocessor.processor.context.handler.ContextModelHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.serviceutil.modelmapper.ModelMapper;
import com.pineone.icbms.so.util.conversion.ProfileTransFormData;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.virtualobject.context.contextmodel.IGenericContextModel;
import com.pineone.icbms.so.virtualobject.location.IGenericLocation;
import com.pineone.icbms.so.virtualobject.profile.IGenericProfile;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;
import com.pineone.icbms.so.web.util.ContextModelMapper2;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

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
    @RequestMapping(value = "/schedule1", method = RequestMethod.POST)
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

        contextLog.debug("called SDA: cm={}, name={}, location={}", contextModelId, contextModelName, locationList.toString());

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
     * response for request "/profile, HTTP-method:POST".<BR/>
     * scheduler가 특정 주기로 호출됨
     *
     * @param profileTransFormData profile
     * @return profile
     */
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public IGenericProfile callFromSchedulerNew(@RequestBody ProfileTransFormData profileTransFormData, HttpServletRequest request) {
        
        if (profileTransFormData==null) {
        	contextLog.warn("Input Profile is null");
        	return null;
        }
        String inputProfileId = profileTransFormData.getId();
    	contextLog.debug("Checking CM by schedule : input profile=[{}]", inputProfileId);

        //Profile Info
    	ProfileForDB profileForDb = databaseManager.getProfileById(inputProfileId);
        IGenericProfile profile = ModelMapper.toProfile(profileForDb);
        
        if (profile==null) {
        	contextLog.warn("Profile is null");
        	return null;
        }
        
        //Context Info
        IGenericContextModel contextModel = profile.getContextModel();
        if (contextModel == null) {
        	contextLog.warn( "contextModel is null");
        	return null;
        }

        String contextModelId = contextModel.getId();
        ContextModelForDB cm = databaseManager.getContextModelById(contextModelId);
        String contextModelName = cm.getName();

        String parameterType = profileForDb.getParameterType();
        String profileLocationUri = profile.getLocation().getUri();

        boolean isCmProceed = false; //CM 처리되었나?
    	List<ContextModelContent> contextModelContentList = new ArrayList<>();
    	ContextModelForIf2 contextModelForIf2 = new ContextModelForIf2();
    	contextModelForIf2.setSimulatorType("");
    	contextModelForIf2.setCmd("query");
    	contextModelForIf2.setContextId(contextModelId);

        if (parameterType==null || parameterType.isEmpty()) {
	        //SDA로 부터 CM발생 여부 체크
        	List<String> locationList = new SdaManager().retrieveEventLocationList(contextModelId);
	        
	        contextLog.debug("called SDA: cm={}, name={}, location={}", contextModelId, contextModelName, locationList.toString());

	        //'D', 'L' 이 아니면 location 비교과 profile의 location을 비교하여 해당 profile 실행
	        if (locationList != null && locationList.size() > 0) {
	
	            for (String location : locationList) {
	                if (location !=  null && location.equals(profileLocationUri)) {
	
	                	ContextModelContent contextModelContent = new ContextModelContent();
	                	contextModelContent.setLocationUri(location);
	                	contextModelContentList.add(contextModelContent);
	                	
	                	isCmProceed = true;
	                	
	                    contextLog.warn("O: result: Happen cm=[{}], Location={}", contextModelId, location);
	                }
	            }
	        }
	
        }else if ("DLI".contains(parameterType)) {
        	//'D', 'L' 이면 location 비교를 안하고 무조건 profile의 실행
            List<ContextModelContent> contentList = new SdaManager().retrieveEventList(contextModelId);

	        if (contentList != null && contentList.size() > 0) {
	        	
	        	String cmValue = null;
            	String value;
	            for (ContextModelContent content : contentList) {
	            	String uri;
	            	if (parameterType.equals("D")) {
	            		uri = content.getDeviceUri();
	            	} else if (parameterType.equals("L")) {
	            		uri = content.getLocationUri();
	            	} else if (parameterType.equals("I")) {
	            		uri = content.getSid();
	            	} else
	            		uri = null;
	            	
	            	if (uri==null)
	            		continue;
	            	value = uri.substring(uri.lastIndexOf("/")+1,uri.length());
	            	if (value!=null && !value.isEmpty()) {
		            	if (cmValue==null)
		            		cmValue = value;
		            	else
		            		cmValue += "|" + value;
	            	}
            	}
	        	ContextModelContent contextModelContent = new ContextModelContent();
	        	contextModelContent.setLocationUri(profileLocationUri);
	        	contextModelContentList.add(contextModelContent);
	        	contextModelForIf2.setResultCmValue(cmValue);
	        	isCmProceed = true;
	            contextLog.warn("O: result: Happen cm=[{}], profileLocationUri={}, cmValue={}", contextModelId, profileLocationUri, cmValue);
        	}
        }

        //Location이 없거나 처리되지 않았을때
        if (isCmProceed == false) {
        	contextLog.warn("X: result: Not happened cm=[{}]", contextModelId);
        }

        contextModelForIf2.setContextModelContentList(contextModelContentList);
    	processContextModel(contextModelForIf2,request);

        return profile;
    }
    
    //ContextModel Q에 전달
    private ContextModelForMQ processContextModel(ContextModelForIf2 contextModelForIf, HttpServletRequest request) {
        log.debug("input:ContextModelForIf: {}", contextModelForIf);
        // create a message From ContextModelForMQ for messageQueue, publish to message queue
        // ContextModelForIf --> ContextModelForMQ
        ContextModelForMQ contextModelForMQ = ContextModelMapper2.toContextModelForMQ(contextModelForIf);

        // tracking
        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
        trackingEntity.setSimulatorType(contextModelForIf.getSimulatorType());  // simulator type 지정
        contextModelForMQ.setTrackingEntity(trackingEntity);

        contextModelForMQ.addState(Const.CONTEXTMODEL_ID, contextModelForIf.getContextId());
        contextModelForMQ.addState(Const.RESULT_CM_VALUE, contextModelForIf.getResultCmValue());
        
        log.debug("converted:ContextModelForMQ: {}", contextModelForMQ);
        //object to json
        String contextModelForMqString = ContextModelMapper2.writeJsonString(contextModelForMQ);
        log.debug("generated:ContextModelForMQ {}", contextModelForMqString);
        //context model producer handler
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "contextmodel");
        Future<RecordMetadata> future = producerHandler.send(contextModelForMQ);
        producerHandler.close();
        log.debug("producer.send result: {}", future);

        return contextModelForMQ;
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

    /**
     * 상황모델 시뮬레이션
     * response for request "/service/context/cm/simulate, HTTP-method:POST".<BR/>
     *
     * @param contextModelForIf ContextModelForIf
     * @return List<TrackingEntity>
     */
    @GetMapping(value = "/checkall")
    public String checkAllProfile(HttpServletRequest request) {

    	StringBuffer strBuf = new StringBuffer();
        String result=null;

        List<ProfileForDB> allProfileForDB = databaseManager.getAllProfile();
    	
    	for (ProfileForDB profileForDb : allProfileForDB) {

    		String contextModelId = profileForDb.getContextModelId();
        	IGenericProfile profile = ModelMapper.toProfile(profileForDb);

    		//SDA로 부터 CM발생 여부 체크
    		List<String> locationList = new SdaManager().retrieveEventLocationList(contextModelId);
    		String buf;
            if (locationList != null && locationList.size() > 0) {
            	buf = String.format("Profile: name=%s, cm=%s, enabled=%d, location=%s\r\n", profile.getName(), contextModelId, profileForDb.getEnabled(),  profile.getLocation().getUri());
            	strBuf.append(buf);

            	for (String location : locationList) {
                    if (location.equals(profile.getLocation().getUri())) {
                    	buf = String.format("  SDA: O: %s\r\n", location);
                    }else {
                    	buf = String.format("  SDA: X: %s\r\n", location);
                    }
                	strBuf.append(buf);
                }
            }
        }
    	result = strBuf.toString();

/*
  		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
*/        
        return result;
    }
}
