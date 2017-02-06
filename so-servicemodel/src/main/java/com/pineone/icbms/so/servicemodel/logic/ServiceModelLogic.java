package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;

import java.util.List;

public interface ServiceModelLogic {
    //
    List<String> retrieveServiceNameList();
    String registerServiceModel(ServiceModel serviceModel);
    ServiceModel retrieveServiceModelDetail(String serviceModelId);
    void executeServiceModel(String serviceModelId, String sessionId);
    List<String> retrieveServiceIdList();
    List<String> retrieveServiceModelIdList();
    List<ServiceModel> retrieveServiceModelList();
    String retreveServiceModelId(String serviceModelName);
    ServiceModel retrieveServiceModelIdByDes(String description);
}
