package com.pineone.icbms.so;

import com.pineone.icbms.so.context.contextmodel.ContextModel;
import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.type.ContextType;
import com.pineone.icbms.so.context.general.GeneralContext;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 28..
 */
public class ContextModelTest {

    @Before
    //NOTE: GeneralContext Data 셋팅 => 가상 디비 MAP 에 저장
    public void setData(){
        GeneralContext generalContext = GeneralContext.newGeneralContext();

        GeneralContext generalContext1 = GeneralContext.newGeneralContext();
        generalContext1.setName("연기감지");
        generalContext.createGeneralContext(generalContext1);
        GeneralContext generalContext2 = GeneralContext.newGeneralContext();
        generalContext2.setName("고온상황");
        generalContext.createGeneralContext(generalContext2);
        GeneralContext generalContext3 = GeneralContext.newGeneralContext();
        generalContext3.setName("사람없음");
        generalContext.createGeneralContext(generalContext3);
        GeneralContext generalContext4 = GeneralContext.newGeneralContext();
        generalContext4.setName("이상상황");
        generalContext.createGeneralContext(generalContext4);
    }


    @Test
    //NOTE: ContextModel 저작 요청 => 가상 디비 MAP 에서 GeneralContextList 조회
    public void requireContextModelMakingTest() throws Exception {

        System.out.println("*********** GeneralContextList 조회 **************");
        ContextModel contextModel = ContextModel.newContextModel();
        List<GeneralContext> generalContextList = contextModel.retrieveGeneralContextList();
        for(GeneralContext generalContext : generalContextList){
            System.out.println(generalContext.getName());
        }
        System.out.println();
    }

    @Test
    //NOTE: ContextModel 에 등록할 도메인 조회
    public void retrieveDomainListTest() throws Exception {
        System.out.println("*********** DomainList 조회 **************");
        List<Domain> domainList = ContextModel.newContextModel().retrieveDomainList();
        for(Domain domain : domainList) {
            System.out.println(domain.getName());
        }
        System.out.println();
    }

    @Test
    //NOTE: ContextType 조회
    public void retrieveContextType() throws Exception{
        System.out.println("*********** ContextType 조회 **************");
        List<ContextType> contextTypeList = ContextModel.newContextModel().retrieveContextTypeList();
        for(ContextType contextType : contextTypeList){
            System.out.println(contextType);
        }
        System.out.println();
    }

    @Test
    //NOTE : ContextModel 시퀀스
    public void contextModelTotalTest() throws Exception {

        ContextModel contextModel = ContextModel.newContextModel();

        System.out.println("*********** Step1 : GeneralContextList 조회 **************");
        List<GeneralContext> generalContextList = contextModel.retrieveGeneralContextList();
        for(GeneralContext generalContext : generalContextList){
            System.out.println(generalContext.getName());
        }
        System.out.println();

        List<GeneralContext> chooseGeneralContextList = new ArrayList<>();
        chooseGeneralContextList.add(generalContextList.get(3));
        chooseGeneralContextList.add(generalContextList.get(1));
        chooseGeneralContextList.add(generalContextList.get(2));

        System.out.println("*********** Step2 : GeneralContext 선택해서 ContextModel 에 저장 **************");
        contextModel.setGeneralContextList(chooseGeneralContextList);

        for(GeneralContext generalContext : chooseGeneralContextList){
            System.out.println(generalContext.getName());
        }
        System.out.println();

        System.out.println("*********** Step3 : DomainList 조회 **************");
        List<Domain> domainList = ContextModel.newContextModel().retrieveDomainList();
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
        List<ContextType> contextTypeList = ContextModel.newContextModel().retrieveContextTypeList();
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
//        ContextModel.newContextModel().registerContextModel(contextModel);

        System.out.println("*********** Step9 : SDA에 등록 후 SO DB에 저장 **************");
        ContextModel.newContextModel().createContextModel(contextModel);
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("SO에 저장된 ContextModel 확인 **************");

        List<ContextModel> contextModelList = ContextModel.newContextModel().retrieveContextModelList();
        ContextModel saveContextModel = contextModelList.get(0);

        System.out.println(" Name : " + saveContextModel.getName());
        System.out.println(" Type : " + saveContextModel.getContextType());
        for(Domain domain : saveContextModel.getDomainList()){
            System.out.println(" Domain : " + domain.getName());
        }
        for(GeneralContext generalContext : saveContextModel.getGeneralContextList()){
            System.out.println(" GeneralContext " + generalContext.getName());
        }
    }
}
