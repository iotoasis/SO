package com.pineone.icbms.so.virtualobject.store;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;

import java.util.List;

public interface VirtualObjectStore {
    void create(VirtualObject virtualObject);
    VirtualObject retrieveByID(String id);
    List<VirtualObject> retrieveByLocation(String location);

    void update(VirtualObject virtualObject);
    void delete(String id);
}
