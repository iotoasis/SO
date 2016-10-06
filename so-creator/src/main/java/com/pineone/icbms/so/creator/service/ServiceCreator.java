package com.pineone.icbms.so.creator.service;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.pr.ServicePresentation;
import com.pineone.icbms.so.service.pr.ServiceTransFormObject;
import com.pineone.icbms.so.service.ref.DataValidation;
import com.pineone.icbms.so.service.ref.ResponseMessage;
import com.pineone.icbms.so.util.exception.DataLossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by melvin on 2016. 10. 5..
 * NOTE :   저작시 필요한 내용들을 Restful 을 이용해서 외부에 노출
 */

@RestController
@RequestMapping(value = "/service")
@ResponseStatus(value = HttpStatus.OK)
public class ServiceCreator {

    @Autowired
    ServicePresentation servicePresentation;

//    //NOTE: Service 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseMessage registerService(@RequestBody ServiceTransformData serviceTransformData){
//        Service service = dataObjectToServiceModel(serviceTransformData);
//        DataValidation dataValidation = DataValidation.newDataValidation();
//        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
//        try {
//            dataValidation.inspectService(service);
//        } catch (DataLossException e) {
//            responseMessage.setExceptionMessage(e.getMessage());
//            return responseMessage;
//        }
//        String resultMessage = serviceLogic.registerService(service);
//        responseMessage.setMessage(resultMessage);
//        return responseMessage;
//    }
//
//    public Service dataObjectToServiceModel(ServiceTransformData serviceTransformData){
//        if(serviceTransformData == null) return null;
//        return new Service(serviceTransformData.getName(), serviceTransformData.getGeneralObjectId(), serviceTransformData.getFunctionality()
//        );
//    }

}
