package com.pineone.icbms.so.service.proxy;

public interface ServiceProxy {
    void executeVirtualObject(String virtualObjectId,String operation);
    void executeCompositeVirtualObject(String compositevoId,String functionality,String operation);
}
