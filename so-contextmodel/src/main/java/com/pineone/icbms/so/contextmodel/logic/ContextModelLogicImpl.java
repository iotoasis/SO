package com.pineone.icbms.so.contextmodel.logic;

import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.contextmodel.entity.Domain;
import com.pineone.icbms.so.contextmodel.proxy.ContextModelProxy;
import com.pineone.icbms.so.contextmodel.proxy.ContextModelSDAProxy;
import com.pineone.icbms.so.contextmodel.ref.ContextType;
import com.pineone.icbms.so.contextmodel.ref.ResponseMessage;
import com.pineone.icbms.so.contextmodel.store.ContextModelMapStore;
import com.pineone.icbms.so.contextmodel.store.ContextModelStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class ContextModelLogicImpl implements ContextModelLogic {

    public static ContextModelLogicImpl newContextModelLogic(){
        return new ContextModelLogicImpl();
    }

    //NOTE: ContextInformationLogic 컴포넌트에서 퍼블리싱한 정보를 이용해서 ContextInformationLogic 리스트 조회
    public List<String> retrieveContextInformationNameList(){
        //
        ContextInformationPresentation contextInformationPresentation = new ContextInformationPresentation();
        List<String> contextInformationList = contextInformationPresentation.retrieveContextInformationNameList();
        return contextInformationList;
    }

    //NOTE: ContextModel 의 Domain 을 결정하기 위해 DomainList 제공
    public List<Domain> retrieveDomainList(){
        //
        DomainLogic domainLogic = DomainLogicImpl.newDomainLogic();
        List<Domain> domainList = domainLogic.retrieveDomainList();
        return domainList;
    }

    //NOTE: ContextModel 의 ContextType 을 결정하기 위해 ContextTypeList 제공
    public List<ContextType> retrieveContextTypeList(){
        //
        ContextTypeLogic contextTypeLogic = ContextTypeLogicImpl.newContextTypeLogic();
        List<ContextType> contextTypeList = contextTypeLogic.retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 등록정보를 수신받고 TODO : SO DB에 저장하고 SDA 에 등록하며 보여줘야할 내용(결정 필요)을 리턴
    public String registerContextModel(ContextModel contextModel){
        //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        ContextModelProxy contextModelProxy = ContextModelSDAProxy.newContextModelProxy();

        contextModelStore.createContextModel(contextModel);
        contextModelProxy.registerContextModel(contextModel);
        String contextModelResultMessage = responseMessage.contextModelResultMessage(contextModel);
        return contextModelResultMessage;
    }

    // NOTE: DB 에서 ContextModel을 조회해서 Name List 로 변환
    public List<String> retrieveContextModelNameList() {
        //
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        List<ContextModel> contextModelList = contextModelStore.retrieveContextModelList();

        //SDA 에서 조회할 경우
        //ContextModelProxy contextModelProxy = ContextModelSDAProxy.newContextModelProxy();
        //List<ContextModel> contextModelList = contextModelProxy.retrieveContextModelListFromSDA();
        List<String> contextModelNameList = new ArrayList<>();
        for(ContextModel contextModel : contextModelList){
            contextModelNameList.add(contextModel.getName());
        }
        return contextModelNameList;
    }

    // NOTE : DB 에서 ContextModel 상세 조회
    public ContextModel retrieveContextModelDetail(String contextModelName) {
        //
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        ContextModel contextModel = contextModelStore.retrieveContextModelDetail(contextModelName);
        //SDA 에서 조회할 경우
        //ContextModelProxy contextModelProxy = ContextModelSDAProxy.newContextModelProxy();
        //ContextModel contextModel = contextModelProxy.retrieveContextModelDetail(contextModelName);
        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<Domain> isHappenContextModel(String contextModelName){
        ContextModelProxy contextModelProxty = ContextModelSDAProxy.newContextModelProxy();
        List<Domain> domainList = contextModelProxty.retrieveContextModelEvent(contextModelName);
        return domainList;
    }
}
