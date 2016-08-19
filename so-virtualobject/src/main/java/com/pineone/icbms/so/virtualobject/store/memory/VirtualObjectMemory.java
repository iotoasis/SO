package com.pineone.icbms.so.virtualobject.store.memory;

import com.pineone.icbms.so.virtualobject.entity.VirtualObject;
import com.pineone.icbms.so.virtualobject.store.VirtualObjectStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualObjectMemory implements VirtualObjectStore {

    //
    private Map<String,VirtualObject> virtualObjectRepository = new HashMap<>();

    private static VirtualObjectMemory instance;

    private VirtualObjectMemory() {
    }

    public static VirtualObjectMemory getInstance(){
        if(instance == null) {
            instance = new VirtualObjectMemory();
        }
        return instance;
    }

    public void create(VirtualObject virtualObject) {
        virtualObjectRepository.put(virtualObject.getVoId(),virtualObject);
    }

    public VirtualObject retrieveByID(String id) {
        return virtualObjectRepository.get(id);
    }

    public List<VirtualObject> retrievceByLocation(String location) {
        //Logic 필요
        List<VirtualObject> virtualObjects = new ArrayList<>();
        virtualObjectRepository.get(location);
        return virtualObjects;
    }

    public void update(VirtualObject virtualObject) {
        virtualObjectRepository.put(virtualObject.getVoId(),virtualObject);
    }

    public void delete(String id) {
        virtualObjectRepository.remove(id);
    }
}
