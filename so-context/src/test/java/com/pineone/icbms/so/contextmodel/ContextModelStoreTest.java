package com.pineone.icbms.so.contextmodel;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.store.ContextInformationMapStore;
import com.pineone.icbms.so.contextinformation.store.ContextInformationStore;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.entity.Domain;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.store.ContextModelMapStore;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public class ContextModelStoreTest {

    ContextModelPresentation contextModelPresentation = new ContextModelPresentation();

    // NOTE: MAP 디비에 ContextModel 저장
    @Before
    public void setData() throws Exception {

        ContextInformationStore contextInformationStore = ContextInformationMapStore.getInstance();
        contextInformationStore.createContextInformation(new ContextInformation("연기감지"));
        contextInformationStore.createContextInformation(new ContextInformation("고온상황"));
        contextInformationStore.createContextInformation(new ContextInformation("사람없음"));
        contextInformationStore.createContextInformation(new ContextInformation("이상상황"));

        ContextModel contextModel = new ContextModel();
        List<String> contextInformationNameList = contextModelPresentation.requestContextModelMakingController();
        List<String> chooseContextInformationNameList = new ArrayList<>();
        chooseContextInformationNameList.add(contextInformationNameList.get(1));
        chooseContextInformationNameList.add(contextInformationNameList.get(2));
        chooseContextInformationNameList.add(contextInformationNameList.get(3));

        contextModel.setContextInformationList(chooseContextInformationNameList);

        List<Domain> domainList =  contextModelPresentation.retrieveDomainListController();

        List<Domain> chooseDomainList = new ArrayList<>();
        chooseDomainList.add(domainList.get(0));
        chooseDomainList.add(domainList.get(2));
        contextModel.setDomainList(chooseDomainList);

        List<ContextType> contextTypeList = contextModelPresentation.retrieveContextTypeListController();

        ContextType contextType = contextTypeList.get(0);
        contextModel.setContextType(contextType.name());

        contextModel.setName("화재상황");

        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        contextModelStore.createContextModel(contextModel);

        ContextModel contextModel1 = new ContextModel();
        contextModel1.setName("돌발상황");
        contextModelStore.createContextModel(contextModel1);
    }

    @Test
    public void 컨텍스트모델리스트조회() throws Exception {
        List<String> contextModelList = contextModelPresentation.retrieveContextModelList();
        for(String contextModel : contextModelList){
            System.out.println(contextModel);
        }
    }

    @Test
    public void 컨텍스트모델상세조회() throws Exception {
        ContextModel contextModel = contextModelPresentation.retrieveContextModelDetailController("화재상황");
        System.out.println(contextModel.getName());
        System.out.println(contextModel.getContextType());
        for(Domain domain : contextModel.getDomainList()){
            System.out.println(domain.getName());
        }
        for(String contextInformation : contextModel.getContextInformationList()){
            System.out.println(contextInformation);
        }
    }
}
