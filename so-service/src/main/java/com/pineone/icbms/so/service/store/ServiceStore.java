package com.pineone.icbms.so.service.store;

import com.pineone.icbms.so.service.entity.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 8..
 */
public interface ServiceStore {
    //
    void createService(Service service);
    List<Service> retrieveServiceList();
    Service retrieveServiceDetail(String serviceId);
    void updateService(Service service);
}
