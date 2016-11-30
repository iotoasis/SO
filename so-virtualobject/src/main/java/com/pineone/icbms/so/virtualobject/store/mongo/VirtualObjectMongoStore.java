package com.pineone.icbms.so.virtualobject.store.mongo;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VirtualObjectMongoStore implements VirtualObjectStore {

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectMongoStore.class);

    @Autowired
    VirtualObjectRepostory virtualObjectRepostory;

    @Override
    public void create(VirtualObject virtualObject) {
        logger.debug("VirtualObject = " + virtualObject.toString());
        VirtualObjectDataObject v = virtualObjectToDataObject(virtualObject);
        virtualObjectRepostory.save(v);
    }

    @Override
    public VirtualObject retrieveByID(String id) {
        logger.debug("VirtualObject ID = " + id);
        VirtualObjectDataObject v = virtualObjectRepostory.findOne(id);
        return DataObjectToVirtualObject(v);
    }

    @Override
    public List<VirtualObject> retrieveByLocation(String location) {
        logger.debug("Location  = " + location);
        List<VirtualObject> virtualObjects = new ArrayList<>();
        List<VirtualObjectDataObject> virtualObjectDataObjects = virtualObjectRepostory.findByvoLocation(location);

        for(VirtualObjectDataObject v : virtualObjectDataObjects) {
            virtualObjects.add(DataObjectToVirtualObject(v));
        }
        for(VirtualObject virtualObject : virtualObjects){
            logger.debug("VirtualObject = " + virtualObject.toString());
        }

        return virtualObjects;
    }

    @Override
    public List<VirtualObject> retrieveByLocationAndService(String location, String deviceService) {
        logger.debug("Location = " + location + " DeviceService = " + deviceService);
        List<VirtualObject> virtualObjects = new ArrayList<>();
        List<VirtualObjectDataObject> virtualObjectDataObjects = virtualObjectRepostory.findByvoLocationAndFunctionality(location, deviceService);

        for(VirtualObjectDataObject v : virtualObjectDataObjects) {
            virtualObjects.add(DataObjectToVirtualObject(v));
        }

        for(VirtualObject virtualObject : virtualObjects){
            logger.debug("VirtualObject = " + virtualObject.toString());
        }

        return virtualObjects;
    }

    @Override
    public List<VirtualObject> retrieveVirtualObjectList() {
        List<VirtualObject> virtualObjects = new ArrayList<>();
        List<VirtualObjectDataObject> virtualObjectDataObjects = virtualObjectRepostory.findAll();

        for(VirtualObjectDataObject v : virtualObjectDataObjects) {
            virtualObjects.add(DataObjectToVirtualObject(v));
        }

        for(VirtualObject virtualObject : virtualObjects){
            logger.debug("VirtualObject = " + virtualObject.toString());
        }

        return virtualObjects;
    }

    @Override
    public void update(VirtualObject virtualObject) {
        logger.debug("VirtualObject = " + virtualObject.toString());
        VirtualObjectDataObject v = virtualObjectToDataObject(virtualObject);
        virtualObjectRepostory.insert(v);
    }

    @Override
    public void delete(String id) {
        logger.debug("VirtualObject ID = " + id);
        virtualObjectRepostory.delete(id);
    }

    private VirtualObjectDataObject virtualObjectToDataObject(VirtualObject virtualObject) {
        if(virtualObject == null) return null;
        return new VirtualObjectDataObject(virtualObject.getId(), virtualObject.getVoName(), virtualObject.getFunctionality(), virtualObject.getVoDescription(), virtualObject.getVoCreateTime(), virtualObject.getVoExpiredTime(), virtualObject.getDeviceService(), virtualObject.getDeviceId(), virtualObject.getVoCommand(), virtualObject.getVoLocation());
    }

    private VirtualObject DataObjectToVirtualObject(VirtualObjectDataObject virtualObjectDataObject) {
        if(virtualObjectDataObject == null)
        {
            return null;
        }
        return new VirtualObject(virtualObjectDataObject.getId(), virtualObjectDataObject.getVoName(), virtualObjectDataObject.getFunctionality(), virtualObjectDataObject.getVoDescription(), virtualObjectDataObject.getVoCreateTime(), virtualObjectDataObject.getVoExpiredTime(), virtualObjectDataObject.getDeviceService(), virtualObjectDataObject.getDeviceId(), virtualObjectDataObject.getVoCommand(), virtualObjectDataObject.getVoLocation());
    }
}
