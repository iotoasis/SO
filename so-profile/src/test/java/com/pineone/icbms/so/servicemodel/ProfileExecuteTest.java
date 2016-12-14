//package com.pineone.icbms.so.servicemodel;
//
//import com.pineone.icbms.so.contextmodel.entity.ContextModel;
//import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
//import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
//import com.pineone.icbms.so.profile.ProfileApplication;
//import com.pineone.icbms.so.profile.entity.Profile;
//import com.pineone.icbms.so.profile.logic.ProfileLogicImpl;
//import com.pineone.icbms.so.profile.pr.ProfilePresentation;
//import com.pineone.icbms.so.profile.pr.ProfileTransFormData;
//import com.pineone.icbms.so.profile.store.ProfileStore;
//import com.pineone.icbms.so.servicemodel.pr.ServiceModelPresentation;
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
// * Created by melvin on 2016. 8. 16..
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = ProfileApplication.class)
//public class ProfileExecuteTest {
//    //
//    @Autowired
//            ContextModelPresentation contextModelPresentation;
//
//    @Autowired
//    ProfilePresentation profilePresentation;
//
//    @Autowired
//    ServiceModelPresentation serviceModelPresentation;
//
//    @Autowired
//    ProfileStore profileStore;
////    public static void main(String[] args) {
//
//    @Test
//    public void name() throws Exception {
//
//        List<String> profileList = profilePresentation.retrieveProfileIdList();
//        for (String profileId : profileList) {
//            System.out.println(profileId);
//        }
//        Profile profile = profilePresentation.retrieveProfileDetailController("PR-FIRE-ENV");
//
//        Thread profileThread = new Thread(new ProfileLogicImpl());
//        profileThread.start();
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }
//
//        ContextModel contextModel = contextModelPresentation.retrieveContextModelDetailController(profile.getContextModelId());
////        ResponseMessage responseMessage = contextModelPresentation.emergencyContextModel(contextModel);
////        System.out.println(responseMessage.getMessage());
//    }
//
//    @Test
//    public void 큐를제외한서비스실행로직테스트() throws Exception {
//
//        ContextModel contextModel = contextModelPresentation.retrieveContextModelDetailController("CM-FIRE-EMERGENCY");
//        //큐제외
//
//        List<Profile> profileList = profileStore.findByContextModelId("CM-FIRE-EMERGENCY");
//        System.out.println("************************* ProfileSearching *******************");
//        for(Profile profile : profileList){
//            System.out.println(profile.getId());
//            System.out.println(profile.getName());
//            System.out.println(profile.getServiceModelId());
//            System.out.println(profile.getContextModelId());
//            System.out.println(profile.getPeriod());
//
////            for(String domainId : contextModel.getDomainIdList()) {
////                serviceModelPresentation.executeServiceModel(domainId, profile.getServiceModelId());
////            }
//        }
//    }
//
//    @Test
//    public void 일반상황응답테스트() throws Exception {
//
//        ProfileTransFormData profileTransFormData = new ProfileTransFormData("PR-IDEALAIR");
//        profilePresentation.executeScheduleProfile(profileTransFormData);
//
//    }
//
//    @Test
//    public void 일반상황무응답테스트() throws Exception {
//        ProfileTransFormData profileTransFormData = new ProfileTransFormData("PR-TEST");
//        profilePresentation.executeScheduleProfile(profileTransFormData);
//
//    }
//
//    //        //NOTE: 도메인 셋팅
////        Domain domain1 = new Domain("강의실", "http://강의실");
////        Domain domain2 = new Domain("기숙사", "http://기숙사");
////        List<Domain> domainList = new ArrayList<>();
////        domainList.add(domain1);
////        domainList.add(domain2);
////
////        //NOTE : CI 셋팅
////        ContextInformation contextInformation1 = new ContextInformation();
////        ContextInformation contextInformation2 = new ContextInformation();
////        contextInformation1.setName("고온상황");
////        contextInformation2.setName("사람없음");
////        List<String> contextInformationNameList = new ArrayList<>();
////        contextInformationNameList.add(contextInformation1.getName());
////        contextInformationNameList.add(contextInformation2.getName());
////
////        contextModel.setDomainList(domainList);
////        contextModel.setContextInformationList(contextInformationNameList);
////        contextModel.setName("화재상황");
////        contextModel.setContextType("Emergency");
////
////        contextModelPresentation.registerGeneralContextController(contextModel);
////
////        ContextModel contextModel1 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
////        contextModelPresentation.emergencyContextModel(contextModel1);
////
////        Thread profileThread = new Thread(new ProfileLogicImpl());
////        profileThread.start();
////
////        try {
////            Thread.sleep(3000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////            Thread.currentThread().interrupt();
////        }
////
////        ContextModel contextModel1 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
////        contextModelPresentation.emergencyContextModel(contextModel1);
////
////
////        try {
////            Thread.sleep(3000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////            Thread.currentThread().interrupt();
////        }
////
////        ContextModel contextModel2 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
////        ResponseMessage responseMessage = contextModelPresentation.emergencyContextModel(contextModel2);
////        System.out.println(responseMessage.getMessage());
//
//    }
//
////    @Test
////    public void name() throws Exception {
////        ContextModel contextModel = new ContextModel();
////        ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
////
////        //NOTE: 도메인 셋팅
////        Domain domain1 = new Domain("강의실", "http://강의실");
////        Domain domain2 = new Domain("기숙사", "http://기숙사");
////        List<Domain> domainList = new ArrayList<>();
////        domainList.add(domain1);
////        domainList.add(domain2);
////
////        //NOTE : CI 셋팅
////        ContextInformation contextInformation1 = new ContextInformation();
////        ContextInformation contextInformation2 = new ContextInformation();
////        contextInformation1.setName("고온상황");
////        contextInformation2.setName("사람없음");
////        List<String> contextInformationNameList = new ArrayList<>();
////        contextInformationNameList.add(contextInformation1.getName());
////        contextInformationNameList.add(contextInformation2.getName());
////
////        contextModel.setDomainList(domainList);
////        contextModel.setContextInformationList(contextInformationNameList);
////        contextModel.setName("화재상황");
////        contextModel.setContextType("Emergency");
////
////        contextModelPresentation.registerGeneralContextController(contextModel);
////
////        ContextModel contextModel1 = contextModelPresentation.retrieveContextModelDetailController("화재상황");
////        ResponseMessage responseMessage = contextModelPresentation.emergencyContextModel(contextModel1);
////        System.out.println(responseMessage.getMessage());
////    }
//
