package com.pineone.icbms.so.context_model.logic;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_information.pr.ContextInformationPresentation;
import com.pineone.icbms.so.context_model.entity.ContextModel;
import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.ref.ContextType;
import com.pineone.icbms.so.context_model.store.ContextModelMapStore;
import com.pineone.icbms.so.context_model.store.ContextModelStore;
import com.pineone.icbms.so.util.exception.DataLossException;
import com.pineone.icbms.so.util.response.ResponseMessage;
import com.pineone.icbms.so.util.validation.DataValidation;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 */
public class ContextModelLogic {

    public static ContextModelLogic newContextModelLogic(){
        return new ContextModelLogic();
    }

    //NOTE: ContextInformationLogic 컴포넌트에서 퍼블리싱한 정보를 이용해서 ContextInformationLogic 리스트 조회
    public List<ContextInformation> retrieveContextInformationList(){
        ContextInformationPresentation contextInformationPresentation = new ContextInformationPresentation();
        List<ContextInformation> contextInformationList = contextInformationPresentation.retrieveContextInformationList();
        return contextInformationList;
    }

    //NOTE: ContextModel 의 Domain 을 결정하기 위해 DomainList 제공
    public List<Domain> retrieveDomainList(){
        List<Domain> domainList = DomainLogic.newDomainLogic().retrieveDomainList();
        return domainList;
    }

    //NOTE: ContextModel 의 ContextType 을 결정하기 위해 ContextTypeList 제공
    public List<ContextType> retrieveContextTypeList(){
        List<ContextType> contextTypeList = ContextTypeLogic.newContextTypeLogic().retrieveContextTypeList();
        return contextTypeList;
    }

    //NOTE: ContextModel 등록정보를 수신받고 TODO : SO DB에 저장하고 SDA 에 등록하며 보여줘야할 내용(결정 필요)을 리턴
    public ResponseMessage registerContextModel(ContextModel contextModel){
        DataValidation dataValidation = DataValidation.newGeneralContextValidation();
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        try {
            dataValidation.inspectContextModel(contextModel);
        } catch (DataLossException e) {
            responseMessage.setExceptionMessage(e.getMessage());
            return responseMessage;
        }

        contextModelStore.createContextModel(contextModel);
        //TODO : 저작된 ContextModel 을 SDA 에 등록
        responseMessage.setMessage(responseMessage.contextModelResultMessage(contextModel));
        return responseMessage;
    }

    // NOTE: DB 에서 ContextModel List 조회
    public List<ContextModel> retrieveContextModelList() {
        //
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        List<ContextModel> contextModelList = contextModelStore.retrieveContextModelList();
        return contextModelList;
    }

    // NOTE : DB 에서 ContextModel 상세 조회
    public ContextModel retrieveContextModelDetail(String contextModelName) {
        //
        ContextModelStore contextModelStore = ContextModelMapStore.getInstance();
        ContextModel contextModel = contextModelStore.retrieveContextModelDetail(contextModelName);
        return contextModel;
    }

    //NOTE: ContextModel 상황 발생 여부 질의
    public List<Domain> isHappenContextModel(ContextModel contextModel){
        //TODO : PROXY 구현 필요
        return null;
    }
}
