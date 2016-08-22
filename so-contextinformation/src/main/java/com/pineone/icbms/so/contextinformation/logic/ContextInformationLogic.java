package com.pineone.icbms.so.contextinformation.logic;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface ContextInformationLogic {
    //
    List<DeviceObject> retrieveDeviceObjectList();
    List<ConceptService> retrieveConceptService(DeviceObject deviceObject);
    String registerContextInformation(ContextInformation contextInformation);
    ContextInformation retrieveContextInformationDetail(String contextName);
    List<String> retrieveContextInformationNameList();
    List<ContextInformation> retrieveContextInformationList();
    List<String> retrieveContextInformationIdList();
}
