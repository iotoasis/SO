package com.pineone.icbms.so.service.proxy;

public interface ServiceProxy {
    void executeVirtualObject(String virtualObjectId,String operation,String sessionId);
    void executeCompositeVirtualObject(String compositevoId,String functionality,String operation,String sessionId);
}
