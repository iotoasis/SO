package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.service.pr.ServicePresentation;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
import com.pineone.icbms.so.servicemodel.ref.ResponseMessage;
import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
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

    @Test
    public void 서비스모델등록() throws Exception {
        //
        List<String> serviceIdList = serviceModelPresentation.retrieveServiceIdList();
        for(String serviceId : serviceIdList){
            System.out.println(serviceId);
        }

        List<String> chooseServiceList = new ArrayList<>();
        chooseServiceList.add("SONAMOOAIRCON_CONTROL");


        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId("IDEAL_COOL_TEMP");
        serviceModel.setName("최적온도제공");
        serviceModel.setServiceIdList(chooseServiceList);

        ResponseMessage responseMessage = serviceModelPresentation.registerServiceModelController(serviceModel);
        System.out.println(responseMessage.getMessage());
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

        ServiceModel serviceModel = serviceModelPresentation.retrieveServiceModelDetailController("IDEAL_COOL_TEMP");
        System.out.println(serviceModel.getName());
        System.out.println(serviceModel.getId());
        for(String id : serviceModel.getServiceIdList()) {
            System.out.println(id);
        }
    }

    @Test
    public void 서비스모델리스트조회() throws Exception {

        List<String> serviceModelIdList = serviceModelPresentation.retrieveServiceModelList();
        for(String serviceModelId : serviceModelIdList){
            System.out.println(serviceModelId);
        }
    }
}
