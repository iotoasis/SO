package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ContextModelApplication.class)
public class ContextModelPresentationTest {


//    @Autowired
//    ContextModelPresentation contextModelPresentation;
//
//    @Autowired
//    DomainPresentation domainPresentation;
//
//    @Autowired
//    ContextInformationStore contextInformationStore;
//
//    @Test
//    public void domain조회() throws Exception {
//
//        List<Domain> domainList = domainPresentation.retrieveDomainListController();
//        for(Domain domain : domainList){
//            System.out.println(domain.getName());
//            System.out.println(domain.getUri());
//        }
//    }
//
//    @Test
//    public void conceptSerivceList조회() throws Exception {
//
//        List<ContextType> contextTypeList = contextModelPresentation.retrieveContextTypeListController();
//        for(ContextType contextType : contextTypeList){
//            System.out.println(contextType);
//        }
//    }
//
//    @Before
//    public void setData() throws Exception {
//
////        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
//        contextInformationStore.createContextInformation(new ContextInformation("연기감지"));
//        contextInformationStore.createContextInformation(new ContextInformation("고온상황"));
//        contextInformationStore.createContextInformation(new ContextInformation("사람없음"));
//        contextInformationStore.createContextInformation(new ContextInformation("이상상황"));
//    }
//
//    @Test
//    public void contextInformationList조회() throws Exception {
//
//        ContextModel contextModel = new ContextModel();
//        System.out.println("*********** Step1 : ContextInformationList 조회 **************");
//        List<String> contextInformationList = contextModelPresentation.requestContextModelMakingController();
//        for(String contextInformation : contextInformationList){
//            System.out.println(contextInformation);
//        }
//        System.out.println();
//
//        List<String> chooseContextInformationList = new ArrayList<>();
//        chooseContextInformationList.add(contextInformationList.get(1));
//        chooseContextInformationList.add(contextInformationList.get(2));
//        chooseContextInformationList.add(contextInformationList.get(3));
//
//        System.out.println("*********** Step2 : ContextInformationLogic 선택해서 ContextModel 에 저장 **************");
//        contextModel.setContextInformationList(chooseContextInformationList);
//        for(String contextInformation : chooseContextInformationList){
//            System.out.println(contextInformation);
//        }
//        System.out.println();
//
//        System.out.println("*********** Step3 : DomainList 조회 **************");
//        List<Domain> domainList =  domainPresentation.retrieveDomainListController();
//        for(Domain domain : domainList) {
//            System.out.println(domain.getName());
//        }
//        System.out.println();
//
//        System.out.println("*********** Step4 : Domain 선택해서 ContextModel 에 저장 **************");
//        List<Domain> chooseDomainList = new ArrayList<>();
//        chooseDomainList.add(domainList.get(0));
//        chooseDomainList.add(domainList.get(2));
//        contextModel.setDomainList(chooseDomainList);
//
//        for(Domain domain : chooseDomainList){
//            System.out.println(domain.getName());
//        }
//        System.out.println();
//
//        System.out.println("*********** Step5 : ContextType 조회 **************");
//        List<ContextType> contextTypeList = contextModelPresentation.retrieveContextTypeListController();
//        for(ContextType contextType : contextTypeList){
//            System.out.println(contextType);
//        }
//        System.out.println();
//
//        System.out.println("*********** Step6 : ContextType 선택해서 ContextModel 에 저장 **************");
//        ContextType contextType = contextTypeList.get(0);
//        contextModel.setContextType(contextType.name());
//        System.out.println(contextModel.getContextType());
//        System.out.println();
//
//        System.out.println("*********** Step7 : ContextModel 이름 추가해서 SO에 등록 **************");
//        contextModel.setName("화재상황");
//        System.out.println(contextModel.getName());
//        System.out.println();
//
//        System.out.println("*********** Step8 : SO 가 등록받은 ContextModel 을 SDA에 등록 (생략) **************");
////        contextModelPresentation.registerGeneralContextController(contextModel);

    @Autowired
    ContextModelPresentation contextModelPresentation;

    @Autowired
    ContextInformationPresentation contextInformationPresentation;

    @Autowired
    DomainPresentation domainPresentation;


    @Test
    public void CM등록() throws Exception {

        List<String> contextInformationIdList = contextInformationPresentation.retrieveContextInformationIdList();
        for(String id : contextInformationIdList){
            System.out.println(id);
        }

        List<String> domainIdList = domainPresentation.retrieveDomainIdList();
        for (String id : domainIdList){
            System.out.println(id);
        }

        ContextModel contextModel = new ContextModel();
        contextModel.setName("화재응급상황");
        contextModel.setId("CM-FIRE-EMERGENCY");
        contextModel.setContextType(ContextType.EmergencyType.toString());
        contextModel.setContextInformationIdList(contextInformationIdList);
        List<String> chooseDomainList = new ArrayList<>();
        chooseDomainList.add("DO-CLASSROOM");
        contextModel.setDomainIdList(chooseDomainList);

        ResponseMessage responseMessage = contextModelPresentation.registerGeneralContextController(contextModel);

        System.out.println(responseMessage.getMessage());
    }

    @Test
    public void CM개별조회() throws Exception {
        ContextModel contextModel = contextModelPresentation.retrieveContextModelDetailController("CM-FIRE-EMERGENCY");
        System.out.println(contextModel.getId());
        System.out.println(contextModel.getName());
        System.out.println(contextModel.getContextType());
        for(String contextModelId : contextModel.getContextInformationIdList()){
            System.out.println(contextModelId);
        }
        for (String domainId : contextModel.getDomainIdList()){
            System.out.println(domainId);
        }
    }

    @Test
    public void CM전체조회() throws Exception {
        List<String> contextModelIDList = contextModelPresentation.retrieveContextModelIdList();
        for(String contextModelId : contextModelIDList){
            System.out.println(contextModelId);
        }

    }
}
