package com.pineone.icbms.so.context.general.pr;

import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.util.check.DataValidation;
import com.pineone.icbms.so.context.util.response.ResponseMessageToUser;
import com.pineone.icbms.so.context.general.device.ConceptService;
import com.pineone.icbms.so.context.general.device.DeviceObject;
import com.pineone.icbms.so.context.general.exception.DataLossException;
import com.pineone.icbms.so.context.util.address.AddressStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: GeneralContext 저작 관련 User Interface 제공
 */
@Controller
public class UserController_GeneralContext {
    //
    GeneralContext generalContext;

    //NOTE: GeneralContext 생성 요청 -> DeviceObject 리스트 리턴
    @RequestMapping(value = AddressStore.REQUIRE_GENERALCONTEXT, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<DeviceObject> requestGeneralContextMaking(){
        //
        generalContext = generalContext.newGeneralContext();
        List<DeviceObject> deviceObjectList = generalContext.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: DeviceObject 선택 -> DeviceObject 의 ConceptService list 리턴
    @RequestMapping(value = AddressStore.RETRIEVE_CONCEPTSERVICE, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ConceptService> retrieveConceptServiceController(@PathVariable("deviceobject") DeviceObject deviceObject){
        //
        generalContext = generalContext.newGeneralContext();
        List<ConceptService> conceptServiceList = generalContext.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: GeneralContext 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(value = AddressStore.REGISTER_GENERALCONTEXT, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessageToUser registerGeneralContextController(@RequestBody GeneralContext generalContext){
        //
        DataValidation dataValidation = DataValidation.newGeneralContextValidation();
        ResponseMessageToUser responseMessage = ResponseMessageToUser.newResponseMessage();
        try {
            dataValidation.inspectGeneralContext(generalContext);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        generalContext.registerGeneralContext(generalContext);
        responseMessage.setMessage(responseMessage.generalContextResultMessage(generalContext));
        return responseMessage;
    }

    //NOTE: GeneralContextList 조회 -> GeneralContextList 리턴
    @RequestMapping(value = AddressStore.RETRIEVE_GENERALCONTEXT, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<GeneralContext> retrieveGeneralContextListController(){
        //
        generalContext = generalContext.newGeneralContext();
        List<GeneralContext> generalContextList = generalContext.retrieveGeneralContextList();
        return generalContextList;
    }

    //NOTE: GeneralContext 상세 정보 조회 -> 상세정보 리턴
    @RequestMapping(value = AddressStore.RETRIEVE_GENERALCONTEXT_DETAIL, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public GeneralContext retrieveGeneralContextController(@PathVariable("contextname") String contextName){
        //
        generalContext = generalContext.newGeneralContext().retrieveGeneralContext(contextName);
        return generalContext;
    }

}
