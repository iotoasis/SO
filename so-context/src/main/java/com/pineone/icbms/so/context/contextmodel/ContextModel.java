package com.pineone.icbms.so.context.contextmodel;

import com.pineone.icbms.so.context.contextmodel.type.ContextType;
import com.pineone.icbms.so.context.general.GeneralContext;
import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SDA;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SO;
import com.pineone.icbms.so.context.contextmodel.itf.database.MapController_ContextModel;
import com.pineone.icbms.so.context.contextmodel.itf.external.sda.SdaController_ContextModel;
import com.pineone.icbms.so.context.general.itf.external.sda.SdaController_GeneralContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 */
public class ContextModel {

    private String name;
    private List<Domain> domainList;
    private List<GeneralContext> generalContextList;
    private String contextType;
    private List<ContextType> contextTypeArrayList;
    private List<ContextModel> contextModelList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public static ContextModel newContextModel(){
        //
        ContextModel contextModel = new ContextModel();
        return contextModel;
    }

    //NOTE : ContextType 조회 - Emergency or Schedule
    public List<ContextType> retrieveDeviceObjectList(){
        //
        contextTypeArrayList = new ArrayList<ContextType>();
        for(ContextType contextType : ContextType.values()){
            contextTypeArrayList.add(contextType);
        }
        return contextTypeArrayList;
    }

    //NOTE : GeneralContextList 조회 from SO DB
    public List<GeneralContext> retrieveGeneralContextList(){
        //
        GeneralContext generalContext = GeneralContext.newGeneralContext();
        generalContextList =  generalContext.retrieveGeneralContextList();
        return generalContextList;
    }

    //NOTE : GeneralContextList 조회 From SDA
    public List<GeneralContext> retrieveGeneralContextListFromSDA(){
        //
        GeneralContext generalContext = GeneralContext.newGeneralContext();
        generalContextList =  generalContext.retrieveGeneralContextListFromSDA();
        return generalContextList;
    }

    //NOTE : 수동으로 저장된 DomainList 조회
    public List<Domain> retrieveDomainList(){
        //
        Domain domain = Domain_SO.newDomain();
        domainList = domain.retrieveDomainList();
        return domainList;
    }

    //NOTE : SDA 에 저장된 DomainList 조회
    public List<Domain> retrieveDomainFromSDA(){
        //
        Domain domain = Domain_SDA.newDomain();
        domainList = domain.retrieveDomainList();
        return domainList;
    }

    //NOTE: SDA 에 ContextModel 저장
    public void registerContextModel(ContextModel contextModel){
        //
        SdaController_ContextModel.newSdaController_ContextModel().registerContextModel(contextModel);
        this.createContextModel(contextModel);
    }

    //NOTE: DB에 ContextModel 저장
    public void createContextModel (ContextModel contextModel){
        //
        MapController_ContextModel mapController_contextModel = MapController_ContextModel.getInstance();
        mapController_contextModel.createContextModel(contextModel);
    }

    //NOTE: DB 에서 ContextModelList 조회(User 의 조회요청에 따라)
    public List<ContextModel> retrieveContextModelList(){
        //
        MapController_ContextModel mapController_contextModel = MapController_ContextModel.getInstance();
        List<ContextModel> contextModelList = mapController_contextModel.retrieveContextModelList();
        return contextModelList;
    }

    // NOTE: SDA 에서 ContextModelList 조회(SO 에서 주기적으로 동기화시키기 위해서)
    public List<ContextModel> retrieveContextModelListFromSDA(){
        //
        List<Object> objectList = SdaController_ContextModel.newSdaController_ContextModel()
                .retrieveContextModelListFromSDA();
        List<ContextModel> ContextModelList = new ArrayList<>();
        for(Object object : objectList){
            contextModelList.add((ContextModel) object);
        }
        return contextModelList;
    }

    // NOTE: SDA 에서 ContextModel 상세 조회
    public ContextModel retrieveContextModel(String contextModelName){
        //
        ContextModel contextModel = SdaController_ContextModel.newSdaController_ContextModel()
                .retrieveContextModelDetail(contextModelName);
        return contextModel;
    }
}
