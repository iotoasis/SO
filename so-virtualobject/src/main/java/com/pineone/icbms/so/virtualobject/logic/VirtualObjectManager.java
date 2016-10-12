package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

import java.util.List;

public interface VirtualObjectManager {
    VirtualObject searchVirtualObject(String id);
    void deleteVirtualObject(String id);
    List<VirtualObject> searchVirtualObjectList(String location);
    List<VirtualObject> searchVirtualObjectList();
    String requestControlDevice(String voId, String operation, String sessionId);
    void produceVirtualObject(VirtualObject virtualObject);
//    String controlDevice(List<ServiceControl> serviceControls);
}
