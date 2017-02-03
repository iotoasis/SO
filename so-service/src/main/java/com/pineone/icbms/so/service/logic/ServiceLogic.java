package com.pineone.icbms.so.service.logic;


import com.pineone.icbms.so.service.entity.Service;
import com.pineone.icbms.so.service.proxy.DataServiceObject;
import com.pineone.icbms.so.service.ref.ConceptService;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;

import java.util.List;

public interface ServiceLogic {
    List<DeviceObject> retrieveDeviceObjectList();
    List<ConceptService> retrieveConceptService(DeviceObject deviceObject);
    List<Status> retrieveStatusList(ConceptService conceptService);
    String registerService(Service service);
    Service retrieveServiceDetail(String serviceName);
    List<String> retrieveServiceNameList();
    List<String> retrieveServiceIdList();
    void executeService(String serviceId, String sessionId);
    List<Service> retrieveServiceList();
    String requestDataService(DataServiceObject dataServiceObject);
}
