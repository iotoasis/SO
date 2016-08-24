package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.entity.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public interface ServiceModelProxy {
    //
    List<String> retrieveServiceNameList();
    Service retrieveServiceDetail(String serviceId);
    List<String> retrieveServiceIdList();
    void executeService(String serviceId);
}
