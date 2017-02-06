package com.pineone.icbms.so.service.pr;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.logic.ServiceLogic;
import com.pineone.icbms.so.service.proxy.DataServiceObject;
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
 * NOTE: Service 관련 Interface 제공
 */

@RestController
@RequestMapping(value ="/service")
@ResponseStatus(value = HttpStatus.OK)
public class ServicePresentation {

    /**
     * Service 등록
     * Service List 조회
     * Service 상세 조회
     * Service 실행
     */

    public static final Logger logger = LoggerFactory.getLogger(ServicePresentation.class);

    @Autowired
    ServiceLogic serviceLogic;


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

    /**
     * Service 등록
     */
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

    /**
     * Service 상세 조회
     */
    @RequestMapping(value = "/{serviceId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Service retrieveServiceDetailController(@PathVariable String serviceId){
        logger.info(LogPrint.inputInfoLogPrint() + "Service ID = " + serviceId);
        //
        Service service = serviceLogic.retrieveServiceDetail(serviceId);
        logger.debug("Service = " + service.toString());
        return service;
    }

    /**
     * Service 실행
     */
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeService(@RequestBody ServiceTransFormObject serviceTransFormObject){
        // 해당 서비스 아이디로 실행하기.
        logger.info(LogPrint.inputInfoLogPrint() + " Service ID = " + serviceTransFormObject.getId() + " Session ID = " + serviceTransFormObject.getSessionId());
        logger.debug("Service ID = " + serviceTransFormObject.getId() + " Session ID = " + serviceTransFormObject.getSessionId());
        serviceLogic.executeService(serviceTransFormObject.getId(), serviceTransFormObject.getSessionId());
    }

    /**
     * Service List 조회
     */
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

    /**
     * Data Service
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String retrieveServiceList(@RequestBody DataServiceObject dataServiceObject){
        //
        logger.info(LogPrint.inputInfoLogPrint() + "Data Service Start");
        logger.info(LogPrint.inputInfoLogPrint() + "DataServiceObject = " + dataServiceObject.toString());

        String dataServiceData = serviceLogic.requestDataService(dataServiceObject);
        logger.info("DataService Data = " + dataServiceData);
        return dataServiceData;
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

    public ServiceTransFormObject settingServiceId(String serviceId, String sessionId){
        ServiceTransFormObject object = new ServiceTransFormObject();
        object.setId(serviceId);
        object.setSessionId(sessionId);
        return object;
    }

    //NOTE : ServiceName 으로 아이디 조회
    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String retrieveProfileIdByName(@PathVariable("name")String serviceName){
        logger.info(LogPrint.inputInfoLogPrint());
        Service service = serviceLogic.retrieveServiceDetailByName(serviceName);
        return service.getId();
    }


    public Service dataObjectToServiceModel(ServiceTransFormObject serviceTransFormObject){
        if(serviceTransFormObject == null) return null;
        return new Service(serviceTransFormObject.getName(),serviceTransFormObject.getName(),serviceTransFormObject.getVirtualObjectIdList(),serviceTransFormObject.getVirtualObjectService(),serviceTransFormObject.getStatus(),serviceTransFormObject.getCreateTime(),serviceTransFormObject.getModifiedTime(),serviceTransFormObject.getFilterTime());
    }
}
