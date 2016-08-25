//package com.pineone.icbms.so.servicemodel;
//
//import com.pineone.icbms.so.service.entity.Service;
//import com.pineone.icbms.so.service.store.ServiceStore;
//import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
//import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
//import com.pineone.icbms.so.servicemodel.store.ServiceModelStore;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by melvin on 2016. 8. 10..
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = ServiceModelApplication.class)
//public class ServiceModelStoreTest {
//
//    @Autowired
//    ServiceModelPresentation serviceModelPresentation;
//
//    @Autowired
//    ServiceModelStore serviceModelStore;
//
//    @Autowired
//    ServiceStore serviceStore;
//
////    ServiceModelPresentation serviceModelPresentation = new ServiceModelPresentation();
//
//    // NOTE: MAP 디비에 ServiceModel 저장
//    @Before
//    public void setData() throws Exception {
//
////        ServiceStore serviceStore = ServiceMapStore.getInstance();
//        serviceStore.createService(new Service("에어컨가동"));
//        serviceStore.createService(new Service("냉방장치가동"));
//        serviceStore.createService(new Service("선풍기가동"));
//
//        ServiceModel serviceModel = new ServiceModel();
//        List<String> serviceNameList = serviceModelPresentation.requestServiceModelMakingController();
//        List<String> chooseServiceNameList = new ArrayList<>();
//        chooseServiceNameList.add(serviceNameList.get(1));
//        chooseServiceNameList.add(serviceNameList.get(2));
//
//        serviceModel.setServiceIdList(chooseServiceNameList);
//
//        serviceModel.setName("냉방제어");
//
////        ServiceModelStore serviceModelStore = ServiceModelMapStore.getInstance();
//        serviceModelStore.createServiceModel(serviceModel);
//
//        ServiceModel serviceModel1 = new ServiceModel();
//        serviceModel1.setName("습도제어");
//        serviceModelStore.createServiceModel(serviceModel1);
//    }
//
//    @Test
//    public void 서비스모델리스트조회() throws Exception {
//        List<String> serviceModelList = serviceModelPresentation.retrieveServiceModelIdList();
//        for(String contextModel : serviceModelList){
//            System.out.println(contextModel);
//        }
//    }
//
//    @Test
//    public void 서비스모델상세조회() throws Exception {
//        ServiceModel serviceModel = serviceModelPresentation.retrieveServiceModelDetailController("냉방제어");
//        System.out.println("************ ServiceModel Name *************");
//        System.out.println(serviceModel.getName());
//        System.out.println();
//
//        System.out.println("************ ServiceName List *************");
//        for(String serviceName : serviceModel.getServiceIdList()){
//            System.out.println(serviceName);
//        }
//    }
//}
