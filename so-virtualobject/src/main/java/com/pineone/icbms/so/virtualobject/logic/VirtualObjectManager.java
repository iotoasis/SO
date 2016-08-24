package com.pineone.icbms.so.virtualobject.logic;

import com.pineone.icbms.so.virtualobject.entity.ServiceControl;
import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

import java.util.List;

public interface VirtualObjectManager {
    VirtualObject searchVirtualObject(String id);
    void deleteVirtualObject(String id);
    List<VirtualObject> searchVirtualObjectList(String location);
    String requestControlDevice(String voId, String operation);
    void produceVirtualObject(VirtualObject virtulaObject);
    String controlDevice(List<ServiceControl> serviceControls);
}
