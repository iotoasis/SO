package com.pineone.icbms.so.servicemodel.pr;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.entity.Domain;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogic;
import com.pineone.icbms.so.contextmodel.logic.ContextModelLogicImpl;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.store.ContextModelMapStore;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;
import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogic;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogicImpl;
import com.pineone.icbms.so.servicemodel.store.ServiceModelMapStore;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 * NOTE : 서비스 모델에서 제공하는 기능이나 저작을 위한 기능 정의
 */

@Controller
@RequestMapping(value = "/servicemodel")
public class ServiceModelPresentation {
    //
    ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

    //NOTE: ServiceModel 생성 요청 -> ServiceList 리턴
    @RequestMapping(value = "/service")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> requestServiceModelMakingController(){
        //
        List<String> serviceNameList = serviceModelLogic.retrieveServiceNameList();
        return serviceNameList;
    }

//    //NOTE: ServiceModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    @ResponseBody
//    public ResponseMessage registerServiceModelController(@RequestBody ServiceModel serviceModel){
//        //
//        DataValidation dataValidation = DataValidation.newDataValidation();
//        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
//        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();

//        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
//        try {
//            dataValidation.inspectContextModel(contextModel);
//        } catch (DataLossException e) {
//            responseMessage.setExceptionMessage(e.getMessage());
//            return responseMessage;
//        }
//
//        contextModelStore.createContextModel(contextModel);
//        //TODO : 저작된 ContextModel 을 SDA 에 등록
//        String resultMessage = contextModelLogic.registerContextModel(contextModel);
//        responseMessage.setMessage(resultMessage);
//        return responseMessage;
//    }
//
//    //NOTE: ContextModel List 퍼블리싱 -  Profile 생성시 사용
//    public List<String> retrieveContextModelList(){
//        //
//        List<String> contextModelNameList = contextModelLogic.retrieveContextModelNameList();
//        return contextModelNameList;
//    }
//
//    //NOTE: ContextModel 싱세 조회
//    @RequestMapping(value = "{contextmodelname}", method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    @ResponseBody
//    public ContextModel retrieveContextModelDetailController(@PathVariable("contextmodelname")String contextModelName){
//        //
//        ContextModel contextModel = contextModelLogic.retrieveContextModelDetail(contextModelName);
//        return contextModel;
//    }
//
//    //NOTE: ContextModel 상황 발생 여부 질의
//    public List<Domain> isHappenContextModel(String contextModelName){
//        List<Domain> domainList = contextModelLogic.isHappenContextModel(contextModelName);
//        return domainList;
//    }
}
