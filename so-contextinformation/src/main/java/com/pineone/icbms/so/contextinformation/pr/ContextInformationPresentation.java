package com.pineone.icbms.so.contextinformation.pr;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.logic.ContextInformationLogic;
import com.pineone.icbms.so.contextinformation.ref.DataValidation;
import com.pineone.icbms.so.contextinformation.ref.ResponseMessage;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final Logger logger = LoggerFactory.getLogger(ContextInformationPresentation.class);

    @Autowired
    ContextInformationLogic contextInformationLogic;

//    ContextInformationLogic contextInformationLogic = ContextInformationLogicImpl.newContextInformationLogic();

    //NOTE: ContextInformation 생성 요청  -> ContextInformationLogic 에 사용할 가상객체 (VO - CVO) DeviceObject 리스트 리턴
    @RequestMapping(value = "/deviceobject", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<DeviceObject> requestContextInformationMaking() {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<DeviceObject> deviceObjectList = contextInformationLogic.retrieveDeviceObjectList();
        return deviceObjectList;
    }

    //NOTE: ContextInformation 의 가상객체에서 사용할 ConceptService 목록 요청
    @RequestMapping(value = "/conceptservice/{deviceobject}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ConceptService> retrieveConceptServiceController(@PathVariable("deviceobject") DeviceObject deviceObject) {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<ConceptService> conceptServiceList = contextInformationLogic.retrieveConceptService(deviceObject);
        return conceptServiceList;
    }

    //NOTE: ContextInformation 의 입력 정보 작성 후 등록 -> 등록 결과 리턴
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerContextInformationController(@RequestBody ContextInformation contextInformation) {
        //
        logger.info(LogPrint.inputInfoLogPrint());
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
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveContextInformationNameList() {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextInformationList = contextInformationLogic.retrieveContextInformationNameList();
        return contextInformationList;
    }

    //NOTE: ContextInformation 상세 정보 조회 -> 상세정보 리턴
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ContextInformation retrieveGeneralContextController(@PathVariable("id") String contextId) {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        ContextInformation contextInformation = contextInformationLogic.retrieveContextInformationDetail(contextId);
        return contextInformation;
    }

    //NOTE: ContextInformationList 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<ContextInformation> retrieveContextInformationList() {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<ContextInformation> contextInformationList = contextInformationLogic.retrieveContextInformationList();
        return contextInformationList;
    }

    // NOTE: ContextInformation Component 의 DB에 접근해서 CI Id 리스트 조회
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveContextInformationIdList() {
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> contextInformationIdList = contextInformationLogic.retrieveContextInformationIdList();
        return contextInformationIdList;
    }

}
