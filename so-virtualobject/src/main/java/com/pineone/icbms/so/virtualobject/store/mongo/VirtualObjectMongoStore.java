package com.pineone.icbms.so.virtualobject.store.mongo;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VirtualObjectMongoStore implements VirtualObjectStore {

    @Autowired
    VirtualObjectRepostory virtualObjectRepostory;

    @Override
    public void create(VirtualObject virtualObject) {
        VirtualObjectDataObject v = virtualObjectToDataObject(virtualObject);
        virtualObjectRepostory.save(v);
    }

    private VirtualObjectDataObject virtualObjectToDataObject(VirtualObject virtualObject) {
        if(virtualObject == null) return null;
        return new VirtualObjectDataObject(virtualObject.getVoId(), virtualObject.getVoName(), virtualObject.getFunctionality(), virtualObject.getVoDiscription(), virtualObject.getVoCreateTime(), virtualObject.getVoExfiredTime(), virtualObject.getDeviceService(), virtualObject.getDeviceId(), virtualObject.getVoCommand(), virtualObject.getVoLocation());
    }

    @Override
    public VirtualObject retrieveByID(String id) {
        VirtualObjectDataObject v = virtualObjectRepostory.findOne(id);
        return DataObjectToVirtualObject(v);
    }

    private VirtualObject DataObjectToVirtualObject(VirtualObjectDataObject virtualObjectDataObject) {
        if(virtualObjectDataObject == null)
        {
            return null;
        }
        return new VirtualObject(virtualObjectDataObject.getVoId(), virtualObjectDataObject.getVoName(), virtualObjectDataObject.getFunctionality(), virtualObjectDataObject.getVoDiscription(), virtualObjectDataObject.getVoCreateTime(), virtualObjectDataObject.getVoExfiredTime(), virtualObjectDataObject.getDeviceService(), virtualObjectDataObject.getDeviceId(), virtualObjectDataObject.getVoCommand(), virtualObjectDataObject.getVoLocation());
    }

    @Override
    public List<VirtualObject> retrieveByLocation(String location) {
        List<VirtualObject> virtualObjects = new ArrayList<>();
        List<VirtualObjectDataObject> virtualObjectDataObjects = virtualObjectRepostory.findByvoLocation(location);

        for(VirtualObjectDataObject v : virtualObjectDataObjects) {
            virtualObjects.add(DataObjectToVirtualObject(v));
        }

        return virtualObjects;
    }

    @Override
    public void update(VirtualObject virtualObject) {
        VirtualObjectDataObject v = virtualObjectToDataObject(virtualObject);
        virtualObjectRepostory.insert(v);
    }

    @Override
    public void delete(String id) {
        virtualObjectRepostory.delete(id);
    }
}
