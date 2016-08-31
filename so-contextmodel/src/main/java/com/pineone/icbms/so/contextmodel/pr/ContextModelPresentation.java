package com.pineone.icbms.so.contextmodel.pr;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogic;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.ref.DataValidation;
import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
import com.pineone.icbms.so.contextmodel.store.mongo.ContextModelDataObject;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by melvin on 2016. 8. 1..
 */
@Controller
@RequestMapping(value = "/cm")
public class ContextModelPresentation {
    //
    @Autowired ContextModelLogic contextModelLogic;
//    ContextModelLogic contextModelLogic = ContextModelLogicImpl.newContextModelLogic();

    //NOTE: ContextModel 생성 요청 -> ContextInformation 리스트 리턴
    @RequestMapping(value = "/ci", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> requestContextModelMakingController(){
        //
        List<String> contextInformationNameList = contextModelLogic.retrieveContextInformationNameList();
        return contextInformationNameList;
    }

    //NOTE : ContextType 조회
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextType> retrieveContextTypeListController(){
        //
        List<ContextType> contextTypeList = contextModelLogic.retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerGeneralContextController(@RequestBody ContextModel contextModel){
        //
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
    public List<String> retrieveContextModelList(){
        //
        List<String> contextModelNameList = contextModelLogic.retrieveContextModelNameList();
        return contextModelNameList;
    }

    //NOTE: ContextModel 싱세 조회
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextModel retrieveContextModelDetailController(@PathVariable("id")String contextModelId){
        //
        ContextModel contextModel = contextModelLogic.retrieveContextModelDetail(contextModelId);
        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<String> isHappenContextModel(String contextModelId){
        List<String> domainIdList = contextModelLogic.isHappenContextModel(contextModelId);
        return domainIdList;
    }

    //NOTE: Profile 에서 ContextModel 의 타입을 알고 추가 정보 요청 판단을 위해 ContextModelName으로 contextTypeName 조회
    public String retrieveContextModelType(String contextModelName){
        String contextModelType = contextModelLogic.retrieveContextModelType(contextModelName);
        return contextModelType;
    }

    //NOTE: SDA 에서 사용할 Emergency ContextModel 을 수신 받기 위한 인터페이스 제공
    @RequestMapping(value = "/occ", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage emergencyContextModel(@RequestBody ContextModelTransFormObject contextModelTransFormObject){
        //
        System.out.println("************ ContextModel Presentation Receive ContextModel ***********");
        System.out.println("Receive ContextModel ID = " + contextModelTransFormObject.getContextId());
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
        return contextModel;
    }

    //NOTE : DomainList 조회
    @RequestMapping(value = "/domain" , method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomain(){
        List<Domain> domainList = contextModelLogic.retrieveDomainList();
        return domainList;
    }

    //NOTE: ContextModelId List 퍼블리싱 -  Profile 생성시 사용
    public List<String> retrieveContextModelIdList(){
        //
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
            domains.add(content.getUri());
        }
        return domains;
    }
}



