package com.pineone.icbms.so.virtualobject.store;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

import java.util.List;

public interface VirtualObjectStore {
    void create(VirtualObject virtualObject);
    VirtualObject retrieveByID(String id);
    List<VirtualObject> retrieveByLocation(String location);
    List<VirtualObject> retrieveByLocationAndService(String location,String service);
    List<VirtualObject> retrieveByAspectAndFunctionality(String aspect,String functionality);
    List<VirtualObject> retrieveVirtualObjectList();
    void update(VirtualObject virtualObject);
    void delete(String id);
}
