package com.pineone.icbms.so.server.controller;

import com.pineone.icbms.so.iot.resources.message.ResponseMessageSDA;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.rule.RuleProcessor;
import com.pineone.icbms.so.iot.service.orchestration.OrchestrationService;
import com.pineone.icbms.so.iot.service.orchestration.OrchestrationServiceRequestDeliveryMessage;
import com.pineone.icbms.so.iot.service.orchestration.OrchestrationServiceResponseDeliveryMessage;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import com.pineone.icbms.so.server.controller.validation.OccControllerValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Melvin on 2015. 12. 28..
 * 1. Provide URI for receive Data From SDA called Occurrence
 * 2. Make Json Object for Return
 * 3. Inspect Occurrence Data Use occControllerValidationClass, abd Make Exception if has error
 * 4. Deliver Occurrence to RuleProcessor
 */

@Controller
public class OccController {
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

   @RequestMapping(value = "/resource/occ1", method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   @ResponseBody
   public ResponseMessageSDA getOccFromSDA(@RequestBody DefaultOccurrence occurrence) {

       ResponseMessageSDA responseMessageToSDA = new ResponseMessageSDA();

       OccControllerValidation occControllerValidation = new OccControllerValidation();

       OrchestrationService orchestrationService ;


       RuleProcessor ruleProcessor = new RuleProcessor();
       /**
        make OccurrenceId for tracking Occurrence
        */




        occurrence.setId(occurrence.getContextId() + "-" + occurrence.getTime());

       OrchestrationServiceRequestDeliveryMessage req = null;
       OrchestrationServiceResponseDeliveryMessage res = null;
       OrchestrationService service = null;
       int status;
       // --------------------- occurred
       // request message
       req = new OrchestrationServiceRequestDeliveryMessage();
       // set method
       req.addValue(OrchestrationService.KEY_METHOD, OrchestrationService.VALUE_METHOD_OCCUR_A_EVENT);
       // set a occurrence
       req.addValue(OrchestrationService.KEY_OCCURRENCE, occurrence);
       // response message
       res = new OrchestrationServiceResponseDeliveryMessage();
       // service
       service = new OrchestrationService();
       // execute service
       service.onService(req, res);
       // get response status
       status = (int) res.getValue(OrchestrationService.KEY_RESPONSE_STATUS);




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



//       ruleProcessor.executeRule(occurrence);

//       List<DefaultServiceModel> serviceModelList = ruleProcessor.executeRule(occurrence);

//       String serviceId;
//       String serviceFullId = "";
//       DefaultServiceCreator defaultServiceCreator = new DefaultServiceCreator();
//       for(DefaultServiceModel serviceModel : serviceModelList)
//       {
//           DefaultService defaultService = (DefaultService) defaultServiceCreator.createServiceByID(serviceModel.getId());
//           defaultService.addValue(IotServiceContext.ACTION_TARGET_LOCATION, occurrence.getDomains());
//           System.out.println("ServiceCreated...");
//           EmitService.getInstance().addService(defaultService);
//           System.out.println("ServiceAdded...");
//       }

       /** put SuccessCode(2000) into ResponseMessage and Return SDA
        *  Occ를 String으로 변환하여 전송하는 것은 Test 결과를 위한 실행.  */


       String occString = DataConversion.objectToString(occurrence); // + serviceFullId;
       responseMessageToSDA.setCode(successCode);
       responseMessageToSDA.setOccResult(occString);

       return responseMessageToSDA;
   }
}
