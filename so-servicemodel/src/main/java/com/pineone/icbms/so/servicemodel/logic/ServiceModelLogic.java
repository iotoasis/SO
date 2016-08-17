package com.pineone.icbms.so.servicemodel.logic;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;
import com.pineone.icbms.so.servicemodel.ref.ServiceMessage;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public interface ServiceModelLogic {
    //
    List<String> retrieveServiceNameList();
    String registerServiceModel(ServiceModel serviceModel);
    ServiceModel retrieveServiceModelDetail(String serviceModelName);
    void executeEmergencyServiceModel(ServiceMessage serviceMessage);
}
