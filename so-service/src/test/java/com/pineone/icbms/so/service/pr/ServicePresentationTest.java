//package com.pineone.icbms.so.service.pr;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.pineone.icbms.so.service.ServiceApplication;
//import com.pineone.icbms.so.service.entity.Service;
//import com.pineone.icbms.so.service.proxy.RequestData;
//import com.pineone.icbms.so.service.proxy.ServiceSDAProxy;
//import com.pineone.icbms.so.service.ref.ResponseMessage;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by melvin on 2016. 8. 8..
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = ServiceApplication.class)
//public class ServicePresentationTest {
//
//    @Autowired
//    ServicePresentation servicePresentation;
//
//    @Autowired
//    ServiceSDAProxy serviceSDAProxy;
//
////    @Test
////    public void 가상객체목록조회 () throws Exception {
////        //
////        List<DeviceObject> deviceObjectList = servicePresentation.requestServiceMaking();
////        for(DeviceObject deviceObject : deviceObjectList){
////            System.out.println(deviceObject);
////        }
////    }
////
////    @Test
////    //NOTE: DeviceObject 를 선택하고 ConceptServiceList 를 수신
////    public void 가상객체의컨셉서비스조회() throws Exception {
////        //
////        List<ConceptService> conceptServiceList = servicePresentation.retrieveConceptServiceController(
////                VirtualObject.SonamooHeater);
////        for(ConceptService conceptService : conceptServiceList){
////            System.out.println(conceptService);
////        }
////        System.out.println();
////    }
////
////    //NOTE VO의 오퍼레이션 조회
////    @Test
////    public void 오퍼레이션조회() throws Exception {
////        //
////        List<Status> statusList = servicePresentation.retrieveStatusController(AirHeatingControlService.AirHeating_Control_service);
////        for(Status status : statusList){
////            System.out.println(status);
////        }
////        System.out.println();
////    }
//
////    @Before
//    //NOTE : service 정보를 입력후 등록 과정
//    public void Service등록() throws Exception {
//        //
//        long currentTime = System.currentTimeMillis();
//        List<String> serviceList = new ArrayList<>();
//        serviceList.add("CR0001AirCleaner0001");
//        Service service = new Service();
//        service.setName("Pleasant Air Services");
//        service.setId("AIRCLEANER-POWER-CONTROL-SERVICE-001");
//        service.setVirtualObjectIdList(serviceList);
//        service.setVirtualObjectService("switch-control");
//        service.setStatus("ON");
//        service.setCreateTime(currentTime);
//        service.setModifiedTime(currentTime);
//
//        ResponseMessage responseMessage = servicePresentation.registerServiceController(dataObjectToServiceModel(service));
//
//        System.out.println(responseMessage.getMessage());
//    }
//
//    @Test
//    public void 서비스개별조회() throws Exception {
//        Service service = servicePresentation.retrieveServiceDetailController("SONAMOOAIRCON_CONTROL");
//        System.out.println(service.getName());
//        System.out.println(service.getId());
//        System.out.println(service.getVirtualObjectService());
//        System.out.println(service.getVirtualObjectIdList().toString());
//        System.out.println(service.getStatus());
//    }
//
//    @Test
//    public void 서비스아이디리스트조회() throws Exception {
//        List<String> serviceIdList = servicePresentation.retrieveServiceIdList();
//        for(String id : serviceIdList){
//            System.out.println(id);
//        }
//
//    }
//
//    public ServiceTransFormObject dataObjectToServiceModel(Service service){
//        if(service == null) return null;
//        return new ServiceTransFormObject(service.getId(), service.getName(), service.getVirtualObjectIdList(),service.getVirtualObjectService(),service.getStatus(),service.getCreateTime(),service.getModifiedTime(),service.getFilterTime(), "");
//    }
//
//    @Test
//    public void SDA데이터요청확인(){
//        String tempData = "{\"cmd\":\"query\",\"contextId\":\"cm-lack-equipment-count\",\"time\":\"20161025T133726\",\"LackEquipment\":[{\"inv\":\"http://www.iotoasis.org/ontology/inventoryKeyboard_ITBT_606_001\",\"qnt\":\"-49\"},{\"inv\":\"http://www.iotoasis.org/ontology/inventoryPC_ITBT_606_001\",\"qnt\":\"-49\"},{\"inv\":\"http://www.iotoasis.org/ontology/inventoryMouse_ITBT_606_001\",\"qnt\":\"-49\"}]}";
//        Type type = new TypeToken<RequestData>() {
//        }.getType();
//        RequestData requestData = new Gson().fromJson(tempData, type);
//        System.out.println("ahn");
//        System.out.println(requestData.toString());
//        System.out.println("ahn");
//    }
//
//}
