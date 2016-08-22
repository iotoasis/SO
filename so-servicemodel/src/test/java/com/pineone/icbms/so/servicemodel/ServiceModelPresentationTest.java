package com.pineone.icbms.so.servicemodel;

import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.store.ServiceStore;
import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
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
    ServiceStore serviceStore;

//    ServiceModelPresentation serviceModelPresentation = new ServiceModelPresentation();

    @Before
    public void setData() throws Exception {

//        ServiceStore serviceStore = ServiceMapStore.getInstance();
        serviceStore.createService(new Service("에어컨가동"));
        serviceStore.createService(new Service("냉방장치가동"));
        serviceStore.createService(new Service("선풍기가동"));
    }

    @Test
    public void serviceList조회() throws Exception {

        ServiceModel serviceModel = new ServiceModel();
        System.out.println("*********** Step1 : ServiceList 조회 **************");
        List<String> serviceNameList = serviceModelPresentation.requestServiceModelMakingController();
        for(String serviceName : serviceNameList){
            System.out.println(serviceName);
        }
        System.out.println();

        List<String> chooseServiceNameList = new ArrayList<>();
        chooseServiceNameList.add(serviceNameList.get(1));
        chooseServiceNameList.add(serviceNameList.get(2));

        System.out.println("*********** Step2 : ServiceModelLogic 선택해서 ServiceModel 에 저장 **************");
        serviceModel.setServiceNameList(chooseServiceNameList);
        for(String chooseServiceName : chooseServiceNameList){
            System.out.println(chooseServiceName);
        }
        System.out.println();

        System.out.println("*********** Step3 : ServiceModel 이름 추가해서 SO에 등록 **************");
        serviceModel.setName("냉방제어");
        System.out.println(serviceModel.getName());
        System.out.println();

    }
}
