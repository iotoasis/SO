package com.pineone.icbms.so.context_information.logic;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_information.store.ContextInformationMapStore;
import com.pineone.icbms.so.context_information.store.ContextInformationStore;
import com.pineone.icbms.so.context_information.temp.device.ConceptService;
import com.pineone.icbms.so.context_information.temp.device.DeviceCenter;
import com.pineone.icbms.so.context_information.temp.device.DeviceObject;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 29..
 * NOTE : ContextInformation 에 연관된 로직 및 서비스들을 수행하기 위한 객체
 */
public class ContextInformationLogic {

    public static ContextInformationLogic newContextInformationLogic(){
        return new ContextInformationLogic();
    }

    //NOTE: ContextInformation 에 사용할 가상 장치를 고르기 위해 So의 VO-CVO 리스트 조회 (DeviceObject List) 조회
    public List<DeviceObject> retrieveDeviceObjectList(){
        //
        return DeviceCenter.retrieveDeviceObjectList();
    }

    //NOTE: ContextInformation 에 사용할 ConceptService 를 선택하기 위해 가상 장치의 ConceptService List 조회
    public List<ConceptService> retrieveConceptService(DeviceObject deviceObject){
        //
        return DeviceCenter.newDeviceCenter().retrieveConceptServiceList(deviceObject);
    }

    //NOTE: ContextInformation 등록정보를 수신받고 TODO : SO DB에 저장하고 SDA 에 등록하며 보여줘야할 내용(결정 필요)을 리턴
    public ResponseMessage registerGeneralContext(@RequestBody ContextInformation contextInformation){
        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        DataValidation dataValidation = DataValidation.newGeneralContextValidation();
        contextInformation.setId("CI-" + contextInformation.getId());
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        try {
            dataValidation.inspectGeneralContext(contextInformation);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }
        contextInformationStore.createContextInformation(contextInformation);
//      TODO : SDA 에 등록
        responseMessage.setMessage(responseMessage.generalContextResultMessage(contextInformation));
        return responseMessage;
    }
}
