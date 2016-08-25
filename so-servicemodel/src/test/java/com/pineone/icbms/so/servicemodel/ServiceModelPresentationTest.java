package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.service.pr.ServicePresentation;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelTransFormObject;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 10..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ServiceModelApplication.class)
public class ServiceModelPresentationTest {

    @Autowired
    ServiceModelPresentation serviceModelPresentation;

    @Autowired
    ServiceModelStore serviceModelStore;

    @Autowired
    ServicePresentation servicePresentation;

//    @Autowired
//    ServiceStore serviceStore;

//    ServiceModelPresentation serviceModelPresentation = new ServiceModelPresentation();

    @Before
    public void 서비스모델등록() throws Exception {
        //
        List<String> serviceList = new ArrayList<>();
        serviceList.add("AIRCLEANER-POWER-CONTROL-SERVICE-001");


        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId("CLASSROOM-AIRIDEAL-SERVICE");
        serviceModel.setName("강의실 쾌적 공기제공 서비스");
        serviceModel.setServiceIdList(serviceList);
        serviceModel.setCreateTime("201608250930");
        serviceModel.setModifiedTime("201608250930");

        ResponseMessage responseMessage = serviceModelPresentation.registerServiceModelController(ServiceModelToDataObject(serviceModel));
        System.out.println(responseMessage.getMessage());

        List<String> serviceList1 = new ArrayList<>();
        serviceList1.add("SMARTLIGHT-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BLIND-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BEAMPROJECTOR-POWER-CONTROL-SERVICE-001");
        serviceList1.add("BEAMSCREEN-POWER-CONTROL-SERVICE-001");
        serviceModelPresentation.registerServiceModelController(ServiceModelToDataObject(new ServiceModel("CLASSROOM-PRESENTATIONMODE-SERVICE","강의실 발표 도우미 서비스",serviceList1,"201608250930","201608250930")));

    }

    public ServiceModelTransFormObject ServiceModelToDataObject(ServiceModel serviceModel){
        if(serviceModel == null) return null;
        ServiceModelTransFormObject object = new ServiceModelTransFormObject(serviceModel.getId(), serviceModel.getName(), serviceModel.getServiceIdList());
        return object;
    }




//    @Test
//    public void serviceList조회() throws Exception {
//
//        ServiceModel serviceModel = new ServiceModel();
//        System.out.println("*********** Step1 : ServiceList 조회 **************");
//        List<String> serviceNameList = serviceModelPresentation.requestServiceModelMakingController();
//        for(String serviceName : serviceNameList){
//            System.out.println(serviceName);
//        }
//        System.out.println();
//
//        List<String> chooseServiceNameList = new ArrayList<>();
//        chooseServiceNameList.add(serviceNameList.get(1));
//        chooseServiceNameList.add(serviceNameList.get(2));
//
//        System.out.println("*********** Step2 : ServiceModelLogic 선택해서 ServiceModel 에 저장 **************");
//        serviceModel.setServiceIdList(chooseServiceNameList);
//        for(String chooseServiceName : chooseServiceNameList){
//            System.out.println(chooseServiceName);
//        }
//        System.out.println();
//
//        System.out.println("*********** Step3 : ServiceModel 이름 추가해서 SO에 등록 **************");
//        serviceModel.setName("냉방제어");
//        System.out.println(serviceModel.getName());
//        System.out.println();
//
//    }


    @Test
    public void 서비스모델개별조회() throws Exception {

        ServiceModel serviceModel = serviceModelPresentation.retrieveServiceModelDetailController("SM_IDEAL_COOL_TEMP");
        System.out.println(serviceModel.getName());
        System.out.println(serviceModel.getId());
        for(String id : serviceModel.getServiceIdList()) {
            System.out.println(id);
        }
    }

    @Test
    public void 서비스모델리스트조회() throws Exception {

        List<String> serviceModelIdList = serviceModelPresentation.retrieveServiceModelIdList();
        for(String serviceModelId : serviceModelIdList){
            System.out.println(serviceModelId);
        }
    }
}
