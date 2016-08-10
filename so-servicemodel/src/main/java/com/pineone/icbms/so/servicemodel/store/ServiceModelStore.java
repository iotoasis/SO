package com.pineone.icbms.so.servicemodel.store;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public interface ServiceModelStore {
    //
    void createServiceModel(ServiceModel serviceModel);
    List<ServiceModel> retrieveServiceModelList();
    ServiceModel retrieveServiceModelDetail(String ServiceModelName);
}
