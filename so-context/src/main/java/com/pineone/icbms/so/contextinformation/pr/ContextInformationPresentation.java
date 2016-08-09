package com.pineone.icbms.so.contextinformation.pr;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.logic.ContextInformationLogic;
import com.pineone.icbms.so.contextinformation.logic.ContextInformationLogicImpl;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 29..
 *  * NOTE: ContextInformation 저작 관련 User Interface 제공
 */
@Controller
@RequestMapping(value = "/ci")
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
public class ContextInformationPresentation {

    ContextInformationLogic contextInformationLogic = ContextInformationLogicImpl.newContextInformationLogic();

    //NOTE: ContextInformation 생성 요청  -> ContextInformationLogic 에 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = "/deviceobject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<DeviceObject> requestContextInformationMaking(){
        //
        List<DeviceObject> deviceObjectList = contextInformationLogic.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: ContextInformation 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = "/conceptservice/{deviceobject}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ConceptService> retrieveConceptServiceController(@PathVariable("deviceobject") DeviceObject deviceObject){
        //
        List<ConceptService> conceptServiceList = contextInformationLogic.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: ContextInformation 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerContextInformationController(@RequestBody ContextInformation contextInformation){
        //
        DataValidation dataValidation = DataValidation.newDataValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        try {
            dataValidation.inspectGeneralContext(contextInformation);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        String resultMessage = contextInformationLogic.registerContextInformation(contextInformation);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    // NOTE: ContextInformation Component 의 DB에 접근해서 CI Name 리스트 조회
    public List<String> retrieveContextInformationNameList(){
        //
        List<String> contextInformationList = contextInformationLogic.retrieveContextInformationNameList();
        return contextInformationList;
    }

    //NOTE: ContextInformation 상세 정보 조회 -> 상세정보 리턴
    @RequestMapping(value = "/{contextname}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextInformation retrieveGeneralContextController(@PathVariable("contextname") String contextName){
        //
        ContextInformation contextInformation = contextInformationLogic.retrieveContextInformationDetail(contextName);
        return contextInformation;
    }


}
