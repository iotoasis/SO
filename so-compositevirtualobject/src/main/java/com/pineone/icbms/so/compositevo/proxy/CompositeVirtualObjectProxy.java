package com.pineone.icbms.so.compositevo.proxy;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

public interface CompositeVirtualObjectProxy {
    void executeCompositeVO(String virtualObjectId, String operation, String SessionId);
    VirtualObject findVirtualObject(String id);
}
