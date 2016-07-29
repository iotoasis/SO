package com.pineone.icbms.so.context.provider.controller;

import com.pineone.icbms.so.iot.provider.ProfileProvider;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;
import com.pineone.icbms.so.iot.resources.service.ServiceSketch;
import com.pineone.icbms.so.iot.rule.RuleProcessor;
import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.model.repo.profile.DefaultProfileModel;
import com.pineone.icbms.so.resources.model.repo.service.DefaultServiceModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melvin on 2015. 12. 23..
 */
public class RuleProcessorTest {


    DefaultOccurrence occurrence = new DefaultOccurrence();

//    @Test
//    public void getProfileListHappyCase() throws Exception {
//
//        occurrence.setContextId("CM1-1-001");
//
//        profileArrayList = profileTestDB.testDB_getProfile(occurrence.getContextId());
//
//        for (Profile profile : profileArrayList) {
//            assertEquals(profile.getName(),"Profile1");
//            System.out.println(profile.getName());
//        }
//
//        for(Profile profile : profileArrayList){
//            for(DefaultContext contextModel : profile.getContextList()) {
////                assertEquals(contextModel.getDomainId(), "xxx.xxx.xx/xx/xx/classroom101");
////                System.out.println(contextModel.getDomainId());
//            }
//        }
//
//        for(Profile profile : profileArrayList){
//            for(DefaultService serviceModel : profile.getServiceList()){
//                System.out.println(serviceModel.getName());
////            assertEquals(serviceModel.getName(),"Service01");
////            assertEquals(serviceModel.getName(),"Service02");
////            assertEquals(serviceModel.getName(),"Service03");
//            }
//        }
//
//
//    }

//    @Test
//    public void ruleProcessHappyCase() throws Exception {
//
//        occurrence.setContextId("CM-1-1-100");
//        serviceModelList = ruleProcessor.executeRule(occurrence);
//
//        for(DefaultServiceModel serviceModel: serviceModelList){
//            System.out.println(serviceModel.getId());
//        }
//
//    }
//
//    @Test
//    public void NotExistProfileListTest() {
//
//        occurrence.setContextId("CM1-1-002");
//        serviceModelList = ruleProcessor.executeRule(occurrence);
//
//        testStringArrayList.add("NotExist Profile Exception");
//        assertEquals(serviceModelIdArrayList, testStringArrayList);
//    }

//    @Test
//    public void NotExistProfileTest() {
//
//        occurrence.setContextId("CM1-1-003");
//        serviceModelList = ruleProcessor.executeRule(occurrence);
//
//        testStringArrayList.add("NotExist Profile Exception");
//        assertEquals(serviceModelIdArrayList, testStringArrayList);
//    }
//
//    @Test
//    public void NotExistContextInfoTest() {
//
//        occurrence.setContextId("CM1-1-004");
//        serviceModelList = ruleProcessor.executeRule(occurrence);
//
//        testStringArrayList.add("NotExist ContextModelId Exception");
//        assertEquals(serviceModelIdArrayList, testStringArrayList);
//    }
//
//    @Test
//    public void NotExistServiceInfoTest() {
//
//        //dd
//        occurrence.setContextId("CM1-1-005");
//        serviceModelList = ruleProcessor.executeRule(occurrence);
//
//        testStringArrayList.add("NotExist Service Model Exception");
//        assertEquals(serviceModelIdArrayList, testStringArrayList);
//    }

    @Test
    public void ruleProcessorDataBaseTest() throws Exception {

        List<DefaultProfileModel> profileModelArrayList;

        ProfileProvider profileProvider = new ProfileProvider();

        occurrence.setContextId("CM-1-1-199");

        String contextId = occurrence.getContextId();

        profileModelArrayList = profileProvider.getDataListByContextID(contextId,DefaultProfileModel.class);

        for(DefaultProfileModel profileModel : profileModelArrayList){
            System.out.println(profileModel.getId());
            System.out.println(profileModel.getName());

            for(DefaultServiceModel serviceModel : profileModel.getServiceModelList()){
                System.out.println(serviceModel.getId());
            }
        }

    }

    @Test
    public void ruleProcessorTest() throws Exception {

        RuleProcessor ruleProcessor = new RuleProcessor();
//        ServiceSketch serviceSketch;
        DefaultDomain domain = new DefaultDomain();
        ArrayList<DefaultDomain> domainArrayList = new ArrayList<>();
        ServiceSketch serviceSketch;

        occurrence.setContextId("CM-1-1-199");

        domain.setId("http://xxx.xx.xx.xxx.xx.x.xx");
        domainArrayList.add(domain);
        occurrence.setDomains(domainArrayList);

        serviceSketch = ruleProcessor.executeRule(occurrence);


        System.out.println(serviceSketch.getDomainType());

        for(String serviceID : serviceSketch.getServiceModelIdList()){
            System.out.println(serviceID);
        }

        for(DefaultDomain domain1 : serviceSketch.getDomainList()){
            System.out.println(domain1.getId());
        }







//        profileModelArrayList = profileProvider.getDataListByContextID(contextId,DefaultProfileModel.class);
//
//        for(DefaultProfileModel profileModel : profileModelArrayList){
//            System.out.println(profileModel.getId());
//            System.out.println(profileModel.getName());
//
//            for(DefaultServiceModel serviceModel : profileModel.getServiceModelList()){
//                System.out.println(serviceModel.getId());
//            }
//        }

    }
}




