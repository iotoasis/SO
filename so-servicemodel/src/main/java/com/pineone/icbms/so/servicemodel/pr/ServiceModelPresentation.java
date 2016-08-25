package com.pineone.icbms.so.servicemodel.pr;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogic;
import com.pineone.icbms.so.servicemodel.ref.DataValidation;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 * NOTE : 서비스 모델에서 제공하는 기능이나 저작을 위한 기능 정의
 */

@RestController
@RequestMapping(value = "/servicemodel")
public class ServiceModelPresentation {
    //
    @Autowired
            ServiceModelLogic serviceModelLogic;
//    ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

    //NOTE: ServiceModel 생성 요청 -> ServiceList 리턴
    @RequestMapping(value = "/service")
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> requestServiceModelMakingController(){
        //
        List<String> serviceNameList = serviceModelLogic.retrieveServiceNameList();
        return serviceNameList;
    }

    //NOTE: ServiceModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseMessage registerServiceModelController(@RequestBody ServiceModelTransFormObject serviceModelTransFormObject){
        // 외부 데이터 변환
        ServiceModel serviceModel = dataObjectToServiceModel(serviceModelTransFormObject);

        // 외부 데이터 검증.
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


    //NOTE: ServiceModel ID List 퍼블리싱 -  Profile 생성시 사용
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> retrieveServiceModelIdList(){
        //
        List<String> serviceModelList = serviceModelLogic.retrieveServiceModelIdList();
        return serviceModelList;
    }

    //NOTE: ServiceModel List
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ServiceModel> retrieveServiceModelList(){
        //
        return serviceModelLogic.retrieveServiceModelList();
    }


    //NOTE: DB 에서 ServiceModel 상세 조회
    @RequestMapping(value = "{serviceModelId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ServiceModel retrieveServiceModelDetailController(@PathVariable String serviceModelId){
        //
        ServiceModel serviceModel = serviceModelLogic.retrieveServiceModelDetail(serviceModelId);
        return serviceModel;
    }

    //NOTE: 응급상황으로 발생하는 ContextModel 에 따른 ServiceModel 실행
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeServiceModel(@RequestBody String serviceModelId) {
        //
        System.out.println("\n**********  ServiceModel Presentation RequestServiceModel  **********");
        System.out.println("Response ServiceModelID = " + serviceModelId);
        serviceModelLogic.executeServiceModel(serviceModelId);
    }

    //NOTE: 저장되어 있는 Service 들의 ID 조회
    public List<String> retrieveServiceIdList(){
        //
        List<String> serviceIdList = serviceModelLogic.retrieveServiceIdList();
        return serviceIdList;
    }

    public ServiceModel dataObjectToServiceModel(ServiceModelTransFormObject serviceModelTransFormObject){
        if(serviceModelTransFormObject == null) return null;
        return new ServiceModel(serviceModelTransFormObject.getId(), serviceModelTransFormObject.getName(), serviceModelTransFormObject.getServiceIdList());
    }

    //NOTE: TestSetUp
    @RequestMapping(value = "/testsetup", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void testSetUp() {
        //
        serviceModelLogic.testSetUp();
    }


}
