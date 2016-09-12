package com.pineone.icbms.so.compositevo.store;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;

import java.util.List;

public interface CompositeVirtualObjectStore {
    void create(CompositeVirtualObject compositeVirtualObject);
    CompositeVirtualObject retrieveByID(String id);
    List<CompositeVirtualObject> retrieveByLocation(String location);
    List<CompositeVirtualObject> retrieveCompositeVirtualObjectList();
    void update(CompositeVirtualObject compositeVirtualObject);
    void delete(String id);
}
