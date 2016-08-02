package com.pineone.icbms.so.context_model;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_information.store.ContextInformationMapStore;
import com.pineone.icbms.so.context_information.store.ContextInformationStore;
import com.pineone.icbms.so.context_model.entity.ContextModel;
import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.pr.ContextModelPresentation;
import com.pineone.icbms.so.context_model.ref.ContextType;
import com.pineone.icbms.so.context_model.store.ContextModelMapStore;
import com.pineone.icbms.so.context_model.store.ContextModelStore;
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
        List<ContextInformation> contextInformationList = contextModelPresentation.requestContextModelMakingController();
        List<ContextInformation> chooseContextInformationList = new ArrayList<>();
        chooseContextInformationList.add(contextInformationList.get(1));
        chooseContextInformationList.add(contextInformationList.get(2));
        chooseContextInformationList.add(contextInformationList.get(3));

        contextModel.setContextInformationList(chooseContextInformationList);

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
        List<ContextModel> contextModelList = contextModelPresentation.retrieveContextModelList();
        for(ContextModel contextModel : contextModelList){
            System.out.println(contextModel.getName());
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
        for(ContextInformation contextInformation : contextModel.getContextInformationList()){
            System.out.println(contextInformation.getName());
        }
    }
}
