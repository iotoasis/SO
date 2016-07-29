package com.pineone.icbms.so.context_information.pr;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_information.logic.ContextInformationLogic;
import com.pineone.icbms.so.context_information.temp.device.ConceptService;
import com.pineone.icbms.so.context_information.temp.device.DeviceObject;
import com.pineone.icbms.so.util.address.AddressStore;
import com.pineone.icbms.so.util.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 29..
 *  * NOTE: ContextInformation 저작 관련 User Interface 제공
 */
public class ContextInformationPresentation {

    //NOTE: ContextInformation 생성 요청  -> ContextInformation 에 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = AddressStore.REQUIRE_GENERALCONTEXT, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<DeviceObject> requestGeneralContextMaking(){
        //
        List<DeviceObject> deviceObjectList = ContextInformationLogic.newContextInformationLogic().retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: ContextInformation 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = AddressStore.RETRIEVE_CONCEPTSERVICE, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ConceptService> retrieveConceptServiceController(@PathVariable("deviceobject") DeviceObject deviceObject){
        //
        List<ConceptService> conceptServiceList = ContextInformationLogic.newContextInformationLogic().retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: ContextInformation 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(value = AddressStore.REGISTER_GENERALCONTEXT, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerGeneralContextController(@RequestBody ContextInformation contextInformation){
        //
        ResponseMessage responseMessage = ContextInformationLogic.newContextInformationLogic().registerGeneralContext(contextInformation);
        return responseMessage;
    }
}
