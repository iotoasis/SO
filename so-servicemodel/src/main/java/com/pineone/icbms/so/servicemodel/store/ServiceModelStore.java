package com.pineone.icbms.so.servicemodel.store;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;

import java.util.List;

public interface ServiceModelStore {
    //
    void createServiceModel(ServiceModel serviceModel);
    List<ServiceModel> retrieveServiceModelList();
    ServiceModel retrieveServiceModelDetail(String ServiceModelId);
    String retrieveServiceModelId(String serviceModelName);
    ServiceModel retrieveServiceModelDetailByDescription(String description);
}
