package com.pineone.icbms.so.servicemodel.proxy;

import com.pineone.icbms.so.service.entity.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 */
public abstract class AbstractServiceModelProxy implements ServiceModelProxy {

    @Override
    public List<String> retrieveServiceNameList() {
        return null;
    }

    @Override
    public Service retrieveServiceDetail(String serviceName) {
        return null;
    }
}
