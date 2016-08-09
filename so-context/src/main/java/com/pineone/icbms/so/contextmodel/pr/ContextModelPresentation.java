package com.pineone.icbms.so.contextmodel.pr;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.entity.Domain;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogic;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogicImpl;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.store.ContextModelMapStore;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by melvin on 2016. 8. 1..
 */
@Controller
@RequestMapping(value = "/cm")
public class ContextModelPresentation {
    //
    ContextModelLogic contextModelLogic = ContextModelLogicImpl.newContextModelLogic();

    //NOTE: ContextModel 생성 요청 -> ContextInformation 리스트 리턴
    @RequestMapping(value = "/ci", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> requestContextModelMakingController(){
        //
        List<String> contextInformationNameList = contextModelLogic.retrieveContextInformationNameList();
        return contextInformationNameList;
    }

    //NOTE: DomainList 조회
    @RequestMapping(value = "/domain", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        List<Domain> domainList = contextModelLogic.retrieveDomainList();
        return domainList;
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
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        try {
            dataValidation.inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }

        contextModelStore.createContextModel(contextModel);
        //TODO : 저작된 ContextModel 을 SDA 에 등록
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
    @RequestMapping(value = "{contextmodelname}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextModel retrieveContextModelDetailController(@PathVariable("contextmodelname")String contextModelName){
        //
        ContextModel contextModel = contextModelLogic.retrieveContextModelDetail(contextModelName);
        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<Domain> isHappenContextModel(String contextModelName){
        List<Domain> domainList = contextModelLogic.isHappenContextModel(contextModelName);
        return domainList;
    }
}
