package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.virtualobject.pr.VirtualObjectPresentation;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceControlProxy implements ServiceProxy{

    @Autowired
    VirtualObjectPresentation virtualObjectPresentation;

    @Override
    public void executeVirtualObject(String virtualObjectId, String operation) {
        virtualObjectPresentation.requestControlVirtualObject(virtualObjectId, operation);
    }
}
