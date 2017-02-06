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
 * NOTE : 서비스 모델에서 제공하는 기능이나 저작을 위한 기능 정의
 */

@RestController
@RequestMapping(value = "/servicemodel")
public class ServiceModelPresentation {

    /**
     * ServiceModel 등록
     * ServiceModel List 검색
     * ServiceModel 상세 검색
     * ServiceModel ID List 조회
     * ServiceModel 실행
     * Service Name List 조회
     */

    public static final Logger logger = LoggerFactory.getLogger(ServiceModelPresentation.class);

    @Autowired
            ServiceModelLogic serviceModelLogic;
//    ServiceModelLogic serviceModelLogic = ServiceModelLogicImpl.newServiceModelLogic();

    /**
     * Service Name을 조회
     */
    @RequestMapping(value = "/service")
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> requestServiceModelMakingController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> serviceNameList = serviceModelLogic.retrieveServiceNameList();
        if(serviceNameList == null){
            logger.warn("You can not create a service model. ServiceNameList is Null");
            return null;
        }
        logger.debug("ServiceNameList = " + serviceNameList.toString());
        return serviceNameList;
    }

    /**
     * ServiceModel 등록
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseMessage registerServiceModelController(@RequestBody ServiceModelTransFormObject serviceModelTransFormObject){
        //
        if(serviceModelTransFormObject == null){
            logger.warn("You can not run a service model. serviceModel is Null");
            return null;
        }
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


    /**
     * ServiceModel ID List 조회
     */
    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<String> retrieveServiceModelIdList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> serviceModelList = serviceModelLogic.retrieveServiceModelIdList();
        logger.debug("ServiceModelList = " + serviceModelList.toString());
        return serviceModelList;
    }

    /**
     * ServiceModel List 조회
     */
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


    /**
     * ServiceModel 상세 조회 By ID
     */
    @RequestMapping(value = "/{serviceModelId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ServiceModel retrieveServiceModelDetailController(@PathVariable String serviceModelId){
        logger.info(LogPrint.inputInfoLogPrint() + "ServiceModel ID = " + serviceModelId);
        ServiceModel serviceModel = serviceModelLogic.retrieveServiceModelDetail(serviceModelId);
        logger.debug("ServiceModel = " + serviceModel);
        return serviceModel;
    }

    /**
     * ServiceModel ID 조회 By Name
     */
    @RequestMapping(value = "/names/{serviceModelName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String retrieveServiceModelId(@PathVariable String serviceModelName){
        logger.info(LogPrint.inputInfoLogPrint() + "ServiceModel Name = " + serviceModelName);
        String serviceModelId = serviceModelLogic.retreveServiceModelId(serviceModelName);
        logger.debug("ServiceModel Name = " + serviceModelId);
        return serviceModelId;
    }

    /**
     * ServiceModel ID 조회 By Name
     */
    @RequestMapping(value = "/des/{description}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String retrieveServiceModelIdByDes(@PathVariable String description){
        logger.info(LogPrint.inputInfoLogPrint() + "Description = " + description);
        ServiceModel serviceModel = serviceModelLogic.retrieveServiceModelIdByDes(description);
        logger.debug("ServiceModel Id = " + serviceModel.getId());
        return serviceModel.getId();
    }


    //NOTE: 응급상황으로 발생하는 ContextModel 에 따른 ServiceModel 실행
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeServiceModel(@RequestBody ServiceModelTransFormObject serviceModelTransFormObject) {
        //
        logger.info(LogPrint.LogMethodNamePrint());
        logger.debug("ServiceModel Id = " + serviceModelTransFormObject.getId() + " Session ID = " + serviceModelTransFormObject.getSessionId());
        serviceModelLogic.executeServiceModel(serviceModelTransFormObject.getId(), serviceModelTransFormObject.getSessionId());
    }

    public ServiceModel dataObjectToServiceModel(ServiceModelTransFormObject serviceModelTransFormObject){
        if(serviceModelTransFormObject == null) return null;
        return new ServiceModel(serviceModelTransFormObject.getId(), serviceModelTransFormObject.getName(), serviceModelTransFormObject.getServiceIdList(), serviceModelTransFormObject.getCreateTime(), serviceModelTransFormObject.getModifiedTime(), serviceModelTransFormObject.getLocation(), serviceModelTransFormObject.getDescription());
    }

    public ServiceModelTransFormObject settingServiceModelId(String serviceModelId, String sessionId){
        ServiceModelTransFormObject object = new ServiceModelTransFormObject();
        object.setId(serviceModelId);
        object.setSessionId(sessionId);
        return object;
    }


}
