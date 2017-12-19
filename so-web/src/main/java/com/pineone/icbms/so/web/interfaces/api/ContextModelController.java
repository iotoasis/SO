package com.pineone.icbms.so.web.interfaces.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
import com.pineone.icbms.so.interfaces.database.model.ProfileForDB;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.database.ref.DataLossException;
import com.pineone.icbms.so.interfaces.database.ref.DataValidation;
import com.pineone.icbms.so.interfaces.database.service.DataBaseStore;
import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.interfaces.sda.handle.SdaManager;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelContent;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.interfaces.si.handle.DeviceManager;
import com.pineone.icbms.so.serviceprocessor.Const;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.web.model.context.Content;
import com.pineone.icbms.so.web.model.context.ContextModel;
import com.pineone.icbms.so.web.model.context.ContextModelTransFormObject;
import com.pineone.icbms.so.web.model.context.ResponseMessage;
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
//@RequestMapping("/cm")
public class ContextModelController {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TrackingDao trackingDao;
    
    @Autowired
    DataBaseStore dataBaseStore;

//    /**
//     * response for request "/context/cm, HTTP-method:POST".<BR/>
//     *
//     * @param contextModelForIf ContextModelForIf
//     * @return created DeviceControlCallbackForDB id
//     */
//    @RequestMapping(value = "/cm", method = RequestMethod.POST)
//    public String injectContextModel(@RequestBody ContextModelForIf contextModelForIf) {
//        log.debug("input:ContextModelForIf: {}", contextModelForIf);
//        // create a message From ContextModelForMQ for messageQueue, publish to message queue
//        // ContextModelForIf --> ContextModelForMQ
//        ContextModelForMQ contextModelForMQ = ModelMapper.toContextModelForMQ(contextModelForIf);
//        log.debug("converted:ContextModelForMQ: {}", contextModelForMQ);
//        //object to json
//        String contextModelForMqString = ModelMapper.writeJsonString(contextModelForMQ);
//        log.debug("generated:ContextModelForMQ {}", contextModelForMqString);
//        //context model producer handler
////        ContextModelProducerHandler producerHandler = new ContextModelProducerHandler(0);
////        Future<RecordMetadata> future = producerHandler.send(contextModelForMqString);
//        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "contextmodel");
//        Future<RecordMetadata> future = producerHandler.send(contextModelForMQ);
//        log.debug("producer.send result: {}", future);
//        producerHandler.close();
//
//        //TODO: 추후 처리 결과를 정의하여 리턴함.
//        return contextModelForMqString;
//    }

    /**
     * response for request "/service/context/cm, HTTP-method:POST".<BR/>
     *
     * @param contextModelForIf ContextModelForIf
     * @return created DeviceControlCallbackForDB id
     */
    //@PostMapping()
    public ContextModelForMQ injectContextModel(@RequestBody ContextModelForIf2 contextModelForIf, HttpServletRequest request) {

        ContextModelForMQ contextModelForMQ = processContextModel(contextModelForIf, request);

//        log.debug("input:ContextModelForIf: {}", contextModelForIf);
//        // create a message From ContextModelForMQ for messageQueue, publish to message queue
//        // ContextModelForIf --> ContextModelForMQ
//        ContextModelForMQ contextModelForMQ = ModelMapper.toContextModelForMQ(contextModelForIf);
//
//        // tracking
//        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
//        trackingEntity.setSimulatorType(contextModelForIf.getSimulatorType());  // simulator type 지정
//        contextModelForMQ.setTrackingEntity(trackingEntity);
//
//        log.debug("converted:ContextModelForMQ: {}", contextModelForMQ);
//        //object to json
//        String contextModelForMqString = ModelMapper.writeJsonString(contextModelForMQ);
//        log.debug("generated:ContextModelForMQ {}", contextModelForMqString);
//        //context model producer handler
//        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "contextmodel");
//        Future<RecordMetadata> future = producerHandler.send(contextModelForMQ);
//        producerHandler.close();
//        log.debug("producer.send result: {}", future);

        return contextModelForMQ;
    }

    /**
     * 상황모델 시뮬레이션
     * response for request "/service/context/cm/simulate, HTTP-method:POST".<BR/>
     *
     * @param contextModelForIf ContextModelForIf
     * @return List<TrackingEntity>
     */
    @PostMapping(value = "/cm/simulate")
    //public List<TrackingEntity> simulateContextModel(@RequestBody ContextModelForIf2 contextModelForIf, HttpServletRequest request) {
    public String simulateContextModel(@RequestBody ContextModelForIf2 contextModelForIf, HttpServletRequest request) {

        ContextModelForMQ contextModelForMQ = processContextModel(contextModelForIf, request);

        String sessionId = contextModelForMQ.getTrackingEntity().getSessionId();

        // polling...
        try {
        	
        	for(int i=0;i<10;i++) {
        		//status_cd = F 인경우를 찾는다.
            	Integer teCheck = trackingDao.retrieveTrackingBySessionIdStatusFinish(sessionId);
            	if(teCheck != null && teCheck > 0 ) break;
        		Thread.sleep(1000);
            }
        } catch (InterruptedException e) { }

        List<TrackingEntity> list = trackingDao.retrieveTrackingBySessionId(sessionId);
        //return list;
        String result=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			//mapper.enable(SerializationFeature.INDENT_OUTPUT);        
			//result = mapper.writeValueAsString(list);
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }

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
    
    //@PostMapping(value = "/test")
    public void testCode(HttpServletRequest request) {
    	DeviceManager.testmain(null);
    }
    
    /**
     * NOTE: SDA 에서 사용할 Emergency ContextModel 을 수신 받기 위한 인터페이스 제공
     *
     * @param contextModelForIf
     * @param request
     * @return
     */
    @RequestMapping(value = "/cm/occ", method = RequestMethod.POST)
    public ResponseMessage emergencyContextModel(@RequestBody ContextModelForIf2 contextModelForIf , HttpServletRequest request) {
        
    	String contextModelId = contextModelForIf.getContextId();
    	String occTime = contextModelForIf.getTime();
    	List<ContextModelContent> contents = contextModelForIf.getContextModelContentList();
		String cmValue = null;

    	log.debug("called /cm/occ ContextModelId = {}", contextModelId);
        log.debug("ContextModel = {}", contextModelForIf.toString());
    	
    	List<ContextModelContent> contentList = new ArrayList<>();
    	List<ProfileForDB> profiles;
    	for (ContextModelContent content : contents) {
    		
    		String locationUri = content.getLocationUri();
    		if (locationUri==null || locationUri.isEmpty())
    			continue;
    		profiles = dataBaseStore.getProfileListByContextModelSidAndLocationUri(contextModelId, locationUri);
    		if (profiles.size()>0) {
        		ContextModelContent newContent = new ContextModelContent();
        		newContent.setLocationUri(content.getLocationUri());
        		contentList.add(newContent);
    		}
    	}
    	
    	if (contentList.size()==0) {
    		List<ProfileForDB> profileForDbList = dataBaseStore.getProfileListByContextModelSidAndLocationUri(contextModelId, null);
    		if (profileForDbList!=null && profileForDbList.size()>0) {

    			ProfileForDB profileForDb = profileForDbList.get(0);
    	        String parameterType = profileForDb.getParameterType();
    	        String profileLocationUri = profileForDb.getLocationUri();
    			
    	        if ("DLI".contains(parameterType)) {
    	        	//'D', 'L' 이면 location 비교를 안하고 무조건 profile의 실행

    	        		String value;

    		            for (ContextModelContent content : contents) {
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
    		        	contentList.add(contextModelContent);

    		            log.debug("added : cm=[{}], profileLocationUri={}, cmValue={}", contextModelId, profileLocationUri, cmValue);
    	        	}
    		}
    	}
    	    
        //
        ContextModelForIf2 contextModelForIf2 = new ContextModelForIf2();
	    contextModelForIf2.setId(contextModelId);
	    contextModelForIf2.setContextId(contextModelId);
	    contextModelForIf2.setTime(occTime);
	    contextModelForIf2.setContextModelContentList(contentList);
    	contextModelForIf2.setResultCmValue(cmValue);

        ContextModelForMQ contextModelForMQ = processContextModel(contextModelForIf2, request);
        ResponseMessage responseMessage = new ResponseMessage();

        TrackingEntity trackingEntity = contextModelForMQ.getTrackingEntity();
        String sessionId = trackingEntity.getSessionId();

        String code;
        if (contentList.size()>0)
        	code = "200";
        else
        	code = "400";
        responseMessage.setCode(code);
        responseMessage.setMessage(sessionId);
        
        return responseMessage;
    }
    
    private DataValidation newDataValidation(){
        DataValidation dataValidation = new DataValidation();
        return dataValidation;
    }
	
	private void inspectContextModel(ContextModel contextModel) throws DataLossException {
        //
        if(contextModel.getId() == null || contextModel.getContextType() == null ||
                contextModel.getDomainIdList() == null){
            throw new DataLossException();
        }
    }

/*	
    private ContextModel dataObjectToContextModel(ContextModelTransFormObject contextModelDataObject){
        if(contextModelDataObject == null) return null;
        return new ContextModel(contextModelDataObject.getContextId(), contentsToStringList(contextModelDataObject.getContents()),
                contextModelDataObject.getCmd(), contextModelDataObject.getTime());
    }
*/    
    private List<String> contentsToStringList(List<Content> contentsList){
        
        List<String> domains = new ArrayList<>();
        for(Content content : contentsList){
            domains.add(content.getLoc());
        }
        return domains;
    }
    
    public String useQueueSaveContextModel(ContextModel contextModel) {
        //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
//        logger.debug("ContextModel = " + contextModel.toString());
//        CONTEXT_MODEL_QUEUE.offer(contextModel);
        
        String contextModelResultMessage = responseMessage.contextModelResultMessage(contextModel);
        return contextModelResultMessage;
    }
    
    public String contextModelResultMessage(ContextModel contextModel){
        String message = " Id : " + contextModel.getId() + " time: " + contextModel.getOccTime();
        return message;
    }
}
