package com.pineone.icbms.so.service.pr;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.logic.ServiceLogic;
import com.pineone.icbms.so.service.logic.ServiceLogicImpl;
import com.pineone.icbms.so.service.ref.ConceptService;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 5..
 * NOTE: Service 관련 Interface 제공
 */
@Controller
@RequestMapping(value = "/service")
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
public class ServicePresentation {

    ServiceLogic serviceLogic = ServiceLogicImpl.newServiceLogicImpl();

    //NOTE: Service 생성 요청  -> ServiceLogic 에서 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = "/deviceobject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<DeviceObject> requestServiceMaking(){
        //
        List<DeviceObject> deviceObjectList = serviceLogic.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = "/conceptservice/{deviceobject}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ConceptService> retrieveConceptServiceController(@PathVariable("deviceobject") DeviceObject deviceObject){
        //
        List<ConceptService> conceptServiceList = serviceLogic.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: Service 의 가상객체에서 사용할 ConceptService 의 Status 리스트 조회
    @RequestMapping(value = "/{conceptservice}/status", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Status> retrieveStatusController(@PathVariable("conceptservice") ConceptService conceptService){
        //
        List<Status> statusLis = serviceLogic.retrieveStatusList(conceptService);
        return statusLis;
    }

    //NOTE: Service 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerServiceController(@RequestBody Service service){
        //
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
    @RequestMapping(value = "/{servicename}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Service retrieveServiceDetailController(@PathVariable("servicename") String serviceName){
        //
        Service service = serviceLogic.retrieveServiceDetail(serviceName);
        return service;
    }
}
