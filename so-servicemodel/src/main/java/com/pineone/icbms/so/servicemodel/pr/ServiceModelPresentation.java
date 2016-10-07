package com.pineone.icbms.so.servicemodel.pr;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.logic.ServiceModelLogic;
import com.pineone.icbms.so.servicemodel.ref.DataValidation;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(ServiceModelPresentation.class);

    @Autowired
            ServiceModelLogic serviceModelLogic;
//    ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

    //NOTE: ServiceModel 생성 요청 -> ServiceList 리턴
    @RequestMapping(value = "/service")
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> requestServiceModelMakingController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> serviceNameList = serviceModelLogic.retrieveServiceNameList();
        logger.debug("ServiceNameList = " + serviceNameList.toString());
        return serviceNameList;
    }

    //NOTE: ServiceModel 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseMessage registerServiceModelController(@RequestBody ServiceModelTransFormObject serviceModelTransFormObject){
        //
        logger.info(LogPrint.inputInfoLogPrint() + "ServiceModel ID = " + serviceModelTransFormObject.getId());
        logger.debug("ServiceModel = " + serviceModelTransFormObject.toString());
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
    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> retrieveServiceModelIdList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> serviceModelList = serviceModelLogic.retrieveServiceModelIdList();
        logger.debug("ServiceModelList = " + serviceModelList.toString());
        return serviceModelList;
    }

    //NOTE: ServiceModel List
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ServiceModel> retrieveServiceModelList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());

        List<ServiceModel> serviceModelList = serviceModelLogic.retrieveServiceModelList();

        for(ServiceModel serviceModel : serviceModelList){
            logger.debug("ServiceModelList = " + serviceModel.toString());
        }
        return serviceModelList;
    }


    //NOTE: DB 에서 ServiceModel 상세 조회
    @RequestMapping(value = "/{serviceModelId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ServiceModel retrieveServiceModelDetailController(@PathVariable String serviceModelId){
        logger.info(LogPrint.inputInfoLogPrint() + "ServiceModel ID = " + serviceModelId);
        //
        ServiceModel serviceModel = serviceModelLogic.retrieveServiceModelDetail(serviceModelId);
        logger.debug("ServiceModel = " + serviceModel);
        return serviceModel;
    }

    //NOTE: 응급상황으로 발생하는 ContextModel 에 따른 ServiceModel 실행
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeServiceModel(@RequestBody ServiceModelTransFormObject serviceModelTransFormObject) {
        //
        logger.info(LogPrint.inputInfoLogPrint() + "ServiceModel ID = " + serviceModelTransFormObject.getId());
        logger.debug("ServiceModel Id = " + serviceModelTransFormObject.getId());
        serviceModelLogic.executeServiceModel(serviceModelTransFormObject.getId());
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

    public ServiceModelTransFormObject settingServiceModelId(String serviceModelId, String sessionId){
        ServiceModelTransFormObject object = new ServiceModelTransFormObject();
        object.setId(serviceModelId);
        return object;
    }


}
