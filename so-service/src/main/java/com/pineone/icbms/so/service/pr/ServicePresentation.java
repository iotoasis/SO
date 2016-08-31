package com.pineone.icbms.so.service.pr;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.logic.ServiceLogic;
import com.pineone.icbms.so.service.ref.*;
import com.pineone.icbms.so.util.exception.DataLossException;
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

    @Autowired
    ServiceLogic serviceLogic;
//    ServiceLogic serviceLogic = ServiceLogicImpl.newServiceLogicImpl();

    //NOTE: Service 생성 요청  -> ServiceLogic 에서 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = "/deviceobject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DeviceObject> requestServiceMaking(){
        //
        List<DeviceObject> deviceObjectList = serviceLogic.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = "/conceptservice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<ConceptService> retrieveConceptServiceController(@RequestBody DeviceObject deviceObject){
        //
        List<ConceptService> conceptServiceList = serviceLogic.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 의 Status 리스트 조회
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Status> retrieveStatusController(@RequestBody ConceptService conceptService){
        //
        List<Status> statusLis = serviceLogic.retrieveStatusList(conceptService);
        return statusLis;
    }

    //NOTE: Service 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseMessage registerServiceController(@RequestBody ServiceTransFormObject serviceTransFormObject){
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
        //
        Service service = serviceLogic.retrieveServiceDetail(serviceId);
        return service;
    }

    //NOTE : Service 실행
    @RequestMapping(value = "/control", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void executeService(@RequestBody ServiceTransFormObject serviceTransFormObject){
        // 해당 서비스 아이디로 실행하기.
        System.out.println("\n**********  Service Presentation RequestServiceControl  **********");
        System.out.println("Response ServiceID = " + serviceTransFormObject.getId());
        serviceLogic.executeService(serviceTransFormObject.getId());
    }

    //NOTE : Service List 조회
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Service> retrieveServiceList(){
        //
        return serviceLogic.retrieveServiceList();
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

    //NOTE : testSetup
    @RequestMapping(value = "/testsetup", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void testSetup(){
        //
        serviceLogic.testSetUp();
    }

    public ServiceTransFormObject settingServiceId(String serviceId){
        ServiceTransFormObject object = new ServiceTransFormObject();
        object.setId(serviceId);
        return object;
    }


    public Service dataObjectToServiceModel(ServiceTransFormObject serviceTransFormObject){
        if(serviceTransFormObject == null) return null;
        return new Service(serviceTransFormObject.getId(), serviceTransFormObject.getName(), serviceTransFormObject.getDeviceObjectId(), serviceTransFormObject.getConceptServiceId(), serviceTransFormObject.getStatus(), serviceTransFormObject.getCreateTime(), serviceTransFormObject.getModifiedTime());
    }

}
