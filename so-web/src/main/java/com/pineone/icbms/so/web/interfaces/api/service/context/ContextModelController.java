package com.pineone.icbms.so.web.interfaces.api.service.context;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import com.pineone.icbms.so.interfaces.database.dao.TrackingDao;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.database.ref.DataLossException;
import com.pineone.icbms.so.interfaces.database.ref.DataValidation;

import com.pineone.icbms.so.interfaces.messagequeue.model.ContextModelForMQ;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf;
import com.pineone.icbms.so.interfaces.sda.model.ContextModelForIf2;
import com.pineone.icbms.so.interfaces.si.handle.DeviceManager;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import com.pineone.icbms.so.web.tracking.BeforeTtrackingHandler;
import com.pineone.icbms.so.web.util.ModelMapper;
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
@RequestMapping("/cm")
public class ContextModelController {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TrackingDao trackingDao;

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
    @PostMapping()
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
    @PostMapping(value = "/simulate")
    public List<TrackingEntity> simulateContextModel(@RequestBody ContextModelForIf2 contextModelForIf, HttpServletRequest request) {

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

        	
        return list;
    }

    @PostMapping(value = "/test")
    public void testCode(HttpServletRequest request) {
    	DeviceManager.testmain(null);
    }
    
    private ContextModelForMQ processContextModel(ContextModelForIf2 contextModelForIf, HttpServletRequest request) {
        log.debug("input:ContextModelForIf: {}", contextModelForIf);
        // create a message From ContextModelForMQ for messageQueue, publish to message queue
        // ContextModelForIf --> ContextModelForMQ
        ContextModelForMQ contextModelForMQ = ModelMapper.toContextModelForMQ(contextModelForIf);

        // tracking
        TrackingEntity trackingEntity = (TrackingEntity) request.getSession().getAttribute("tracking");
        trackingEntity.setSimulatorType(contextModelForIf.getSimulatorType());  // simulator type 지정
        contextModelForMQ.setTrackingEntity(trackingEntity);

        log.debug("converted:ContextModelForMQ: {}", contextModelForMQ);
        //object to json
        String contextModelForMqString = ModelMapper.writeJsonString(contextModelForMQ);
        log.debug("generated:ContextModelForMQ {}", contextModelForMqString);
        //context model producer handler
        DefaultProducerHandler producerHandler = new DefaultProducerHandler(0, "contextmodel");
        Future<RecordMetadata> future = producerHandler.send(contextModelForMQ);
        producerHandler.close();
        log.debug("producer.send result: {}", future);

        return contextModelForMQ;
    }
    
    /**
     * NOTE: SDA 에서 사용할 Emergency ContextModel 을 수신 받기 위한 인터페이스 제공
     *
     * @param contextModelTransFormObject
     * @param request
     * @return
     */
    @RequestMapping(value = "/occ", method = RequestMethod.POST)
    public ResponseMessage emergencyContextModel(@RequestBody ContextModelTransFormObject contextModelTransFormObject
		    , HttpServletRequest request) {
        
        log.info("ContextModelId = {}", contextModelTransFormObject.getContextId());
        log.debug("ContextModel = {}", contextModelTransFormObject.toString());
        //
        DataValidation dataValidation = newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ContextModel contextModel = dataObjectToContextModel(contextModelTransFormObject);
        try {
            inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
    
        ContextModelForIf2 contextModelForIf2 = new ContextModelForIf2();
	    contextModelForIf2.setId(contextModel.getId());
	    contextModelForIf2.setTime(contextModel.getOccTime());
	    // TODO contextModel(2차년 모델) -> contextModelForIf2(3차년 모델) 변환
	    // TODO 나머지 프라퍼티 변환해서 셋팅...
	    // cmid는 한개고요, domains는 ArrayList<Map<String, String»값을 보냅니다.(sda 박부장 답변)
			    
        ContextModelForMQ contextModelForMQ = processContextModel(contextModelForIf2, request);
        
        String resultMessage = useQueueSaveContextModel(contextModel);
        responseMessage.setMessage(resultMessage);
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
    
    private ContextModel dataObjectToContextModel(ContextModelTransFormObject contextModelDataObject){
        if(contextModelDataObject == null) return null;
        return new ContextModel(contextModelDataObject.getContextId(), contentsToStringList(contextModelDataObject.getContents()),
                contextModelDataObject.getCmd(), contextModelDataObject.getTime());
    }
    
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
