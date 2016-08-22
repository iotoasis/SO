package com.pineone.icbms.so.servicemodel.pr;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogic;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogicImpl;
import com.pineone.icbms.so.servicemodel.ref.DataValidation;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.ref.ServiceMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
            ServiceModelLogic serviceModelLogic;
//    ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

    //NOTE: ServiceModel 생성 요청 -> ServiceList 리턴
    @RequestMapping(value = "/service")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> requestServiceModelMakingController(){
        //
        List<String> serviceNameList = serviceModelLogic.retrieveServiceNameList();
        return serviceNameList;
    }

    //NOTE: ServiceModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerServiceModelController(@RequestBody ServiceModel serviceModel){
        //
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();

        try {
            dataValidation.inspectServiceModel(serviceModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = serviceModelLogic.registerServiceModel(serviceModel);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: ServiceModel List 퍼블리싱 -  Profile 생성시 사용
    public List<String> retrieveServiceModelList(){
        //
        List<String> serviceModelList = serviceModelLogic.retrieveServiceNameList();
        return serviceModelList;
    }

    //NOTE: DB 에서 ServiceModel 상세 조회
    @RequestMapping(value = "{servicemodelname}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ServiceModel retrieveServiceModelDetailController(@PathVariable("servicemodelname")String serviceModelName){
        //
        ServiceModel serviceModel = serviceModelLogic.retrieveServiceModelDetail(serviceModelName);
        return serviceModel;
    }

    //NOTE: 응급상황으로 발생하는 ContextModel 에 따른 ServiceModel 실행
    public void executeEmergencyServiceModel(List<Domain> domainList, String serviceModelName) {
        //
        ServiceMessage serviceMessage = new ServiceMessage(domainList, serviceModelName);
        serviceModelLogic.executeEmergencyServiceModel(serviceMessage);
    }


}
