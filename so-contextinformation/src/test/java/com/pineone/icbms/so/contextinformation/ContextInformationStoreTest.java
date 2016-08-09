package com.pineone.icbms.so.contextinformation;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.store.ContextInformationMapStore;
import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
import com.pineone.icbms.so.contextinformation.temp.device.TempConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.VirtualObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: DB 저장 및 조회
 */
public class ContextInformationStoreTest {

    //NOTE: MAP DB 에 데이터 저장 Test
    @Before
    public void registerContextInformationTest() throws Exception {

        ContextInformationMapStore contextInformationStore = ContextInformationMapStore.getInstance();
        //User Field
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

        // NOTE: DB에 저장
        contextInformationStore.createContextInformation(contextInformation);
        ContextInformation contextInformation1 = ContextInformation.newContextInformation();
        contextInformation1.setName("2번째 데이터");
        contextInformationStore.createContextInformation(contextInformation1);
    }

    @Test
    public void ContextInformation리스트조회() throws Exception {

        ContextInformationMapStore contextInformationStore = ContextInformationMapStore.getInstance();
        // NOTE: DB에 저장되어 있는 데이터들을 조회
        List<ContextInformation> contextInformationList = contextInformationStore.retrieveContextInformationList();

        for (ContextInformation contextInfo : contextInformationList){
            System.out.println(contextInfo.getName());
        }
    }

    @Test
    //NOTE : ContextInformationLogic 상세 조회 테스트
    public void ContextInformation상세조회() throws Exception {
        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        ContextInformation contextInformation = contextInformationStore.retrieveContextInformationDetail("EmergencyTempContext");

        System.out.println(contextInformation.getName());
        System.out.println(contextInformation.getDeviceObject());
        System.out.println(contextInformation.getConceptService());
        System.out.println(contextInformation.getId());
        System.out.println(contextInformation.getMinValue());
        System.out.println(contextInformation.getMaxValue());
    }
}
