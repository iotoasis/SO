package com.pineone.icbms.so.service.pr;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.logic.ServiceLogic;
import com.pineone.icbms.so.service.ref.*;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 5..
 * NOTE: Service 관련 Interface 제공
 */
@RestController
@RequestMapping(value = "/service")
@ResponseStatus(value = HttpStatus.OK)
public class ServicePresentation {

    public static final Logger logger = LoggerFactory.getLogger(ServicePresentation.class);

    @Autowired
    ServiceLogic serviceLogic;
//    ServiceLogic serviceLogic = ServiceLogicImpl.newServiceLogicImpl();

    //NOTE: Service 생성 요청  -> ServiceLogic 에서 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = "/controlservice", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DeviceObject> requestServiceMaking(){
        logger.info(LogPrint.inputInfoLogPrint());
        //
        List<DeviceObject> deviceObjectList = serviceLogic.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = "/controlservice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ConceptService> retrieveConceptServiceController(@RequestBody DeviceObject deviceObject){
        logger.info(LogPrint.inputInfoLogPrint());
        // TODO : functionality 목록들을 표시 된다.(저작시 필요)
        List<ConceptService> conceptServiceList = serviceLogic.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 의 Status 리스트 조회
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Status> retrieveStatusController(@RequestBody ConceptService conceptService){
        logger.info(LogPrint.inputInfoLogPrint());
        //
        List<Status> statusList = serviceLogic.retrieveStatusList(conceptService);
        logger.debug("StatusList = " + statusList.toString());
        return statusList;
    }

    //NOTE: Service 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseMessage registerServiceController(@RequestBody ServiceTransFormObject serviceTransFormObject){
        logger.info(LogPrint.inputInfoLogPrint() +" Service ID = " + serviceTransFormObject.getId());
        logger.debug("Service = " + serviceTransFormObject.toString());
        //
        Service service = dataObjectToServiceModel(serviceTransFormObject);
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        try {
            dataValidation.inspectService(service);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = serviceLogic.registerService(service);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: Service 상세 정보 조회 -> 상세정보 리턴
    @RequestMapping(value = "/{serviceId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Service retrieveServiceDetailController(@PathVariable String serviceId){
        logger.info(LogPrint.inputInfoLogPrint() + "Service ID = " + serviceId);
        //
        Service service = serviceLogic.retrieveServiceDetail(serviceId);
        logger.debug("Service = " + service.toString());
        return service;
    }

    //NOTE : Service 실행
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeService(@RequestBody ServiceTransFormObject serviceTransFormObject){
        // 해당 서비스 아이디로 실행하기.
        logger.info(LogPrint.inputInfoLogPrint() + " Service ID = " + serviceTransFormObject.getId());
        logger.debug("Service ID = " + serviceTransFormObject.getId());
        serviceLogic.executeService(serviceTransFormObject.getId());
    }

    //NOTE : Service List 조회
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Service> retrieveServiceList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<Service> serviceList = serviceLogic.retrieveServiceList();
        for(Service service : serviceList){
            logger.debug("ServiceList = " + service.toString());
        }
        return serviceList;
    }


    // NOTE: Service Component 의 DB에 접근해서 service Name 리스트 조회
    public List<String> retrieveServiceNameList(){
        //
        List<String> serviceNameList = serviceLogic.retrieveServiceNameList();
        return serviceNameList;
    }

    // NOTE: Service Component 의 DB에 접근해서 service Id 리스트 조회
    public List<String> retrieveServiceIdList(){
        //
        List<String> serviceIdList = serviceLogic.retrieveServiceIdList();
        return serviceIdList;
    }

    public ServiceTransFormObject settingServiceId(String serviceId){
        ServiceTransFormObject object = new ServiceTransFormObject();
        object.setId(serviceId);
        return object;
    }


    public Service dataObjectToServiceModel(ServiceTransFormObject serviceTransFormObject){
        if(serviceTransFormObject == null) return null;
        return new Service(serviceTransFormObject.getId(), serviceTransFormObject.getName(), serviceTransFormObject.getVirtualObjectIdList(), serviceTransFormObject.getVirtualObjectService(), serviceTransFormObject.getStatus(), serviceTransFormObject.getCreateTime(), serviceTransFormObject.getModifiedTime());
    }
}
