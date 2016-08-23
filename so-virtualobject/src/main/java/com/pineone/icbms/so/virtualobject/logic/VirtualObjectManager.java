package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

import java.util.List;

public interface VirtualObjectManager {
    VirtualObject searchVirtualObject(String id);
    void deleteVirtualObject(String id);
    List<VirtualObject> searchVirtualObjectList(String location);
    String controlDevice(String voId, String operation);
    void produceVirtualObject(VirtualObject virtulaObject);
}
