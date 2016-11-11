//package com.pineone.icbms.so.contextinformation;
//
//import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
//import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
//import com.pineone.icbms.so.contextinformation.ref.ResponseMessage;
//import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
//import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
//import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;
//import com.pineone.icbms.so.contextinformation.temp.device.TempConceptService;
//import com.pineone.icbms.so.contextinformation.temp.device.VirtualObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.List;
//
///**
// * Created by melvin on 2016. 7. 29..
// * NOTE : ContextInformationLogic 관련 테스트
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = ContextInformationApplication.class)
//public class ContextInformationPresentationTest {
//    //
//    @Autowired
//    ContextInformationPresentation contextInformationPresentation;
//
////    @Test
////    public void 가상객체목록조회 () throws Exception {
////        //
////        List<DeviceObject> deviceObjectList = contextInformationPresentation.requestContextInformationMaking();
////        for(DeviceObject deviceObject : deviceObjectList){
////            System.out.println(deviceObject);
////        }
////    }
////
////    @Test
////    //NOTE: DeviceObject 를 선택하고 ConceptServiceList 를 수신
////    public void 가상객체의컨셉서비스조회() throws Exception {
////        //
////        List<ConceptService> conceptServiceList = contextInformationPresentation.retrieveConceptServiceController(
////                VirtualObject.TemperatureSensor001);
////        for(ConceptService conceptService : conceptServiceList){
////            System.out.println(conceptService);
////        }
////        System.out.println();
////    }
//
//    @Autowired
//    ContextInformationStore contextInformationStore ;
//
//    @Before
//    //NOTE : ContextInformationLogic 정보를 입력후 등록 과정
//    public void ContextInformation등록() throws Exception {
//        //User Field
//        String name = "EmergencyTempCon";
//        int minValue = 60;
//        int maxValue = 100;
//        String id = "CI-EMERGENCY-TEMP";
//        DeviceObject deviceObject = VirtualObject.TemperatureSensor001; // 저작시 온도 센서 선택
//        ConceptService conceptService = TempConceptService.temperature_measure_service; // 온도센서의 온도 측정 기능 선택
//
//        ContextInformation contextInformation = new ContextInformation();
//        contextInformation.setId(id);
//        contextInformation.setName(name);
//        contextInformation.setMinValue(minValue);
//        contextInformation.setMaxValue(maxValue);
//        contextInformation.setDeviceObjectName(deviceObject.toString());
//        contextInformation.setConceptServiceName(conceptService.toString());
//
//        // NOTE: DB에 저장
//        ResponseMessage responseMessage = contextInformationPresentation.registerContextInformationController(contextInformation);
//        System.out.println(responseMessage.getMessage());
//
//        ContextInformation contextInformation1 = new ContextInformation();
//        contextInformation1.setId("CI-NOBODY");
//        contextInformation1.setName("사람없음");
//        contextInformation1.setMinValue(0);
//        contextInformation1.setMaxValue(0);
//        contextInformation1.setDeviceObjectName("재실센서");
//        contextInformation1.setConceptServiceName("재실측정");
//
//        ResponseMessage responseMessage1 = contextInformationPresentation.registerContextInformationController(contextInformation1);
//        System.out.println(responseMessage1.getMessage());
//    }
//
//    @Test
//    public void CI개별조회() throws Exception {
//        String contextInformationID = "CI-EMERGENCY-TEMP";
//        ContextInformation contextInformation = contextInformationPresentation.retrieveGeneralContextController(contextInformationID);
//
//        System.out.println(contextInformation.getName());
//        System.out.println(contextInformation.getId());
//        System.out.println(contextInformation.getConceptServiceName());
//        System.out.println(contextInformation.getDeviceObjectName());
//        System.out.println(contextInformation.getMaxValue());
//        System.out.println(contextInformation.getMinValue());
//    }
//
//    @Test
//    public void CI리스트조회() throws Exception {
//
//        List<ContextInformation> contextInformationList = contextInformationPresentation.retrieveContextInformationList();
//        for(ContextInformation contextInformation : contextInformationList){
//            System.out.println(contextInformation.getName());
//            System.out.println(contextInformation.getId());
//            System.out.println(contextInformation.getConceptServiceName());
//            System.out.println(contextInformation.getDeviceObjectName());
//            System.out.println(contextInformation.getMaxValue());
//            System.out.println(contextInformation.getMinValue());
//        }
//    }
//}
