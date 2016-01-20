package com.pineone.icbms.so.server.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pineone.icbms.so.iot.resources.message.ResponseMessageSDA;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.rule.RuleProcessor;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import com.pineone.icbms.so.server.controller.validation.OccControllerValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class OccControllerTemp {
    public static final String successCode = "2000";
    public static final String exceptionCode = "3000";
    public static final String errorCode = "4000";

    /**
     * ResponseMessageSDA : templateClass for Response SDA
     */

//    List<DefaultServiceModel> serviceModelList;
//
//    DefaultServiceModel serviceModel = new DefaultServiceModel();

    /**
     * make Interface for Connecting with SDA, receive Occurrence
     * @param occurrence
     * @return
     */

    @JsonIgnore
    @RequestMapping(value = "/resource/occ", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessageSDA getOccFromSDA(@RequestBody DefaultOccurrence occurrence) {
/**
 * Created by Melvin on 2016. 1. 14..
 * 서버로 인해 임시적으로 SDA에 대비하기 위한 Controller
 */

    ResponseMessageSDA responseMessageToSDA = new ResponseMessageSDA();

    OccControllerValidation occControllerValidation = new OccControllerValidation();


    RuleProcessor ruleProcessor = new RuleProcessor();
    /**
     make OccurrenceId for tracking Occurrence
     */




    occurrence.setId(occurrence.getContextId() + "-" + occurrence.getTime());

    /**
     * use occControllerValidation that include Defined Exception.
     * if happen Exception Error , insert into ResponseMessage and Return SDA
     * can check ExceptionContents in View.(Client)
     */
    try{
        occControllerValidation.inspectOccController(occurrence);
    }catch (Exception e) {
//            e.printStackTrace();
        responseMessageToSDA.setCode(exceptionCode);
        responseMessageToSDA.setMessage(e.getMessage());
        return responseMessageToSDA;
    }



//    ruleProcessor.executeRule(occurrence);
//
//    List<DefaultServiceModel> serviceModelList = ruleProcessor.executeRule(occurrence);
//
//    String serviceId;
//    String serviceFullId = "";
//    DefaultServiceCreator defaultServiceCreator = new DefaultServiceCreator();
//    for(DefaultServiceModel serviceModel : serviceModelList)
//    {
//        DefaultService defaultService = (DefaultService) defaultServiceCreator.createServiceByID(serviceModel.getId());
//        defaultService.addValue(IotServiceContext.ACTION_TARGET_LOCATION, occurrence.getDomains());
//        System.out.println("ServiceCreated...");
//        EmitService.getInstance().addService(defaultService);
//        System.out.println("ServiceAdded...");
//    }

    /** put SuccessCode(2000) into ResponseMessage and Return SDA
     *  Occ를 String으로 변환하여 전송하는 것은 Test 결과를 위한 실행.  */


    String occString = DataConversion.objectToString(occurrence); // + serviceFullId;
    responseMessageToSDA.setCode(successCode);
    responseMessageToSDA.setOccResult(occString);


    return responseMessageToSDA;


}
}
