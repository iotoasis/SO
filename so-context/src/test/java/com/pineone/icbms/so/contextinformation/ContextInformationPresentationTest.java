package com.pineone.icbms.so.contextinformation;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
import com.pineone.icbms.so.contextinformation.temp.device.TempConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.VirtualObject;
import com.pineone.icbms.so.util.response.ResponseMessage;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 29..
 * NOTE : ContextInformationLogic 관련 테스트
 */
public class ContextInformationPresentationTest {

    ContextInformationPresentation contextInformationPresentation =
            new ContextInformationPresentation();

    @Test
    public void 가상객체목록조회 () throws Exception {
        //
        List<DeviceObject> deviceObjectList = contextInformationPresentation.requestContextInformationMaking();
        for(DeviceObject deviceObject : deviceObjectList){
            System.out.println(deviceObject);
        }
    }

    @Test
    //NOTE: DeviceObject 를 선택하고 ConceptServiceList 를 수신
    public void 가상객체의컨셉서비스조회() throws Exception {
        //
        List<ConceptService> conceptServiceList = contextInformationPresentation.retrieveConceptServiceController(
                VirtualObject.TemperatureSensor001);
        for(ConceptService conceptService : conceptServiceList){
            System.out.println(conceptService);
        }
        System.out.println();
    }

    @Test
    //NOTE : ContextInformationLogic 정보를 입력후 등록 과정
    public void ContextInformation등록() throws Exception {
        //
        //User field
        String name = "EmergencyTempContext";
        int minValue = 60;
        int maxValue = 100;
        String id = "EMERGENCY-TEMP";
        DeviceObject deviceObject = VirtualObject.TemperatureSensor001; // 저작시 온도 센서 선택
        ConceptService conceptService = TempConceptService.temperature_measure_service; // 온도센서의 온도 측정 기능 선택

        ContextInformation contextInformation = ContextInformation.newContextInformation();
        contextInformation.setId(id);
        contextInformation.setName(name);
        contextInformation.setMinValue(minValue);
        contextInformation.setMaxValue(maxValue);
        contextInformation.setDeviceObject(deviceObject);
        contextInformation.setConceptService(conceptService);

//        ResponseMessage responseMessage = contextInformationPresentation.registerContextInformationController(contextInformation);
//        System.out.println(responseMessage.getMessage());
    }
}
