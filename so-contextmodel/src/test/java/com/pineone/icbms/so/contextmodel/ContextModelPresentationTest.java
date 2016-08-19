package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.store.ContextInformationMapStore;
import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class ContextModelPresentationTest {

    ContextModelPresentation contextModelPresentation = new ContextModelPresentation();
    DomainPresentation domainPresentation = new DomainPresentation();

    @Test
    public void domain조회() throws Exception {

        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        for(Domain domain : domainList){
            System.out.println(domain.getName());
            System.out.println(domain.getUri());
        }
    }

    @Test
    public void conceptSerivceList조회() throws Exception {

        List<ContextType> contextTypeList = contextModelPresentation.retrieveContextTypeListController();
        for(ContextType contextType : contextTypeList){
            System.out.println(contextType);
        }
    }

    @Before
    public void setData() throws Exception {

        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        contextInformationStore.createContextInformation(new ContextInformation("연기감지"));
        contextInformationStore.createContextInformation(new ContextInformation("고온상황"));
        contextInformationStore.createContextInformation(new ContextInformation("사람없음"));
        contextInformationStore.createContextInformation(new ContextInformation("이상상황"));
    }

    @Test
    public void contextInformationList조회() throws Exception {

        ContextModel contextModel = new ContextModel();
        System.out.println("*********** Step1 : ContextInformationList 조회 **************");
        List<String> contextInformationList = contextModelPresentation.requestContextModelMakingController();
        for(String contextInformation : contextInformationList){
            System.out.println(contextInformation);
        }
        System.out.println();

        List<String> chooseContextInformationList = new ArrayList<>();
        chooseContextInformationList.add(contextInformationList.get(1));
        chooseContextInformationList.add(contextInformationList.get(2));
        chooseContextInformationList.add(contextInformationList.get(3));

        System.out.println("*********** Step2 : ContextInformationLogic 선택해서 ContextModel 에 저장 **************");
        contextModel.setContextInformationList(chooseContextInformationList);
        for(String contextInformation : chooseContextInformationList){
            System.out.println(contextInformation);
        }
        System.out.println();

        System.out.println("*********** Step3 : DomainList 조회 **************");
        List<Domain> domainList =  domainPresentation.retrieveDomainListController();
        for(Domain domain : domainList) {
            System.out.println(domain.getName());
        }
        System.out.println();

        System.out.println("*********** Step4 : Domain 선택해서 ContextModel 에 저장 **************");
        List<Domain> chooseDomainList = new ArrayList<>();
        chooseDomainList.add(domainList.get(0));
        chooseDomainList.add(domainList.get(2));
        contextModel.setDomainList(chooseDomainList);

        for(Domain domain : chooseDomainList){
            System.out.println(domain.getName());
        }
        System.out.println();

        System.out.println("*********** Step5 : ContextType 조회 **************");
        List<ContextType> contextTypeList = contextModelPresentation.retrieveContextTypeListController();
        for(ContextType contextType : contextTypeList){
            System.out.println(contextType);
        }
        System.out.println();

        System.out.println("*********** Step6 : ContextType 선택해서 ContextModel 에 저장 **************");
        ContextType contextType = contextTypeList.get(0);
        contextModel.setContextType(contextType.name());
        System.out.println(contextModel.getContextType());
        System.out.println();

        System.out.println("*********** Step7 : ContextModel 이름 추가해서 SO에 등록 **************");
        contextModel.setName("화재상황");
        System.out.println(contextModel.getName());
        System.out.println();

        System.out.println("*********** Step8 : SO 가 등록받은 ContextModel 을 SDA에 등록 (생략) **************");
//        contextModelPresentation.registerGeneralContextController(contextModel);

    }
}
