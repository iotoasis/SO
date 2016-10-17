package com.pineone.icbms.so.service.proxy;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

public interface ServiceProxy {
    void executeVirtualObject(String virtualObjectId,String operation,String sessionId);
    void executeCompositeVirtualObject(String compositevoId,String functionality,String operation,String sessionId);
    VirtualObject findVirtualObject(String virtualObjectId);
}
