package com.pineone.icbms.so.contextmodel.pr;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogic;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.ref.DataValidation;
import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by melvin on 2016. 8. 1..
 */
@RestController
@RequestMapping(value = "/cm")
@ResponseStatus(value = HttpStatus.OK)
public class ContextModelPresentation {
    //
    @Autowired ContextModelLogic contextModelLogic;

    public static final Logger logger = LoggerFactory.getLogger(ContextModelPresentation.class);

//    ContextModelLogic contextModelLogic = ContextModelLogicImpl.newContextModelLogic();

    //NOTE: ContextModel 생성 요청 -> ContextInformation 리스트 리턴
    @RequestMapping(value = "/ci", method = RequestMethod.GET)
    public List<String> requestContextModelMakingController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextInformationNameList = contextModelLogic.retrieveContextInformationNameList();
        return contextInformationNameList;
    }

    //NOTE : ContextType 조회
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public List<ContextType> retrieveContextTypeListController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<ContextType> contextTypeList = contextModelLogic.retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    public ResponseMessage registerGeneralContextController(@RequestBody ContextModel contextModel){
        //
        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " + contextModel.getId());
        logger.debug("ContextModel = " + contextModel.toString());
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        try {
            dataValidation.inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = contextModelLogic.registerContextModel(contextModel);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: ContextModel List 퍼블리싱 -  Profile 생성시 사용
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public List<String> retrieveContextModelList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextModelNameList = contextModelLogic.retrieveContextModelNameList();
        return contextModelNameList;
    }

    //NOTE: ContextModel 싱세 조회
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ContextModel retrieveContextModelDetailController(@PathVariable("id")String contextModelId){
        //
        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " +  contextModelId);
        logger.debug("ContextModelId = " + contextModelId);
        ContextModel contextModel = contextModelLogic.retrieveContextModelDetail(contextModelId);

        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<String> isHappenContextModel(String contextModelId){
        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " + contextModelId);
        logger.debug("ContextModelId = " + contextModelId);
        List<String> domainIdList = contextModelLogic.isHappenContextModel(contextModelId);
        return domainIdList;
    }

    //NOTE: Profile 에서 ContextModel 의 타입을 알고 추가 정보 요청 판단을 위해 ContextModelName으로 contextTypeName 조회
    public String retrieveContextModelType(String contextModelName){
        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " + contextModelName);
        logger.debug("ContextModelName = " + contextModelName);
        String contextModelType = contextModelLogic.retrieveContextModelType(contextModelName);
        return contextModelType;
    }

    //NOTE: SDA 에서 사용할 Emergency ContextModel 을 수신 받기 위한 인터페이스 제공
    @RequestMapping(value = "/occ", method = RequestMethod.POST)
    public ResponseMessage emergencyContextModel(@RequestBody ContextModelTransFormObject contextModelTransFormObject){

        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " + contextModelTransFormObject.getContextId());
        logger.debug("ContextModel = " + contextModelTransFormObject.toString());
        //
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ContextModel contextModel = dataObjectToContextModel(contextModelTransFormObject);
        try {
            dataValidation.inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = contextModelLogic.useQueueSaveContextModel(contextModel);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: ContextModel Queue 에 있는 데이터를 Read
    public ContextModel retrieveContextModelQueueData(){
        ContextModel contextModel = contextModelLogic.retrieveQueueData();
        logger.info(LogPrint.inputInfoLogPrint() + ", ContextModelId = " + contextModel.getId());
        logger.debug("ContextModel = " + contextModel.toString());
        return contextModel;
    }

    //NOTE : DomainList 조회
    @RequestMapping(value = "/domain" , method = RequestMethod.GET)
    public List<Domain> retrieveDomain(){
        logger.info(LogPrint.inputInfoLogPrint());
        List<Domain> domainList = contextModelLogic.retrieveDomainList();
        return domainList;
    }

    //NOTE: ContextModelId List 퍼블리싱 -  Profile 생성시 사용
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public List<String> retrieveContextModelIdList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextModelNameList = contextModelLogic.retrieveContextModelIdList();
        return contextModelNameList;
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

    //NOTE: ContextModelList 조회
    @RequestMapping(method = RequestMethod.GET)
    public List<ContextModel> retrieveContextInformationList() {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<ContextModel> contextModelList = contextModelLogic.retrieveContextInformationList();
        return contextModelList;
    }

    //NOTE: ContextModelName 조회
    public String retrieveContextModelName(String contextModelId) {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        ContextModel contextModel = contextModelLogic.retrieveContextModelDetail(contextModelId);
        return contextModel.getName();
    }
}



