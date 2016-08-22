package com.pineone.icbms.so.service;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.pr.ServicePresentation;
import com.pineone.icbms.so.service.ref.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 8..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ServiceApplication.class)
public class ServicePresentationTest {

    @Autowired
    ServicePresentation servicePresentation;

//    @Test
//    public void 가상객체목록조회 () throws Exception {
//        //
//        List<DeviceObject> deviceObjectList = servicePresentation.requestServiceMaking();
//        for(DeviceObject deviceObject : deviceObjectList){
//            System.out.println(deviceObject);
//        }
//    }
//
//    @Test
//    //NOTE: DeviceObject 를 선택하고 ConceptServiceList 를 수신
//    public void 가상객체의컨셉서비스조회() throws Exception {
//        //
//        List<ConceptService> conceptServiceList = servicePresentation.retrieveConceptServiceController(
//                VirtualObject.SonamooHeater);
//        for(ConceptService conceptService : conceptServiceList){
//            System.out.println(conceptService);
//        }
//        System.out.println();
//    }
//
//    //NOTE VO의 오퍼레이션 조회
//    @Test
//    public void 오퍼레이션조회() throws Exception {
//        //
//        List<Status> statusList = servicePresentation.retrieveStatusController(AirHeatingControlService.AirHeating_Control_service);
//        for(Status status : statusList){
//            System.out.println(status);
//        }
//        System.out.println();
//    }

    @Before
    //NOTE : service 정보를 입력후 등록 과정
    public void Service등록() throws Exception {
        //
        //User field
        String name = "SonamooAircon_Control";
        String id = "SONAMOOAIRCON_CONTROL";
        DeviceObject deviceObject = VirtualObject.SonamooHeater; // 저작시 히터 선택
        ConceptService conceptService = AirHeatingControlService.AirHeating_Control_service; // 히터 서비스 선택
        Status status = Status.Active;

        Service service = new Service();
        service.setName(name);
        service.setId(id);
        service.setDeviceObjectId(deviceObject.toString());
        service.setConceptServiceId(conceptService.toString());
        service.setStatus(status.toString());

        ResponseMessage responseMessage = servicePresentation.registerServiceController(service);
        System.out.println(responseMessage.getMessage());
    }

    @Test
    public void 서비스개별조회() throws Exception {
        Service service = servicePresentation.retrieveServiceDetailController("SONAMOOAIRCON_CONTROL");
        System.out.println(service.getName());
        System.out.println(service.getId());
        System.out.println(service.getConceptServiceId());
        System.out.println(service.getDeviceObjectId());
        System.out.println(service.getStatus());
    }

    @Test
    public void 서비스아이디리스트조회() throws Exception {
        List<String> serviceIdList = servicePresentation.retrieveServiceIdList();
        for(String id : serviceIdList){
            System.out.println(id);
        }

    }
}
