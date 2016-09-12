package com.pineone.icbms.so.compositevo.store.mongo;

import com.pineone.icbms.so.compositevo.entity.CompositeVirtualObject;
import com.pineone.icbms.so.compositevo.store.CompositeVirtualObjectStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompositeVirtualObjectMongoStore implements CompositeVirtualObjectStore{

    @Autowired
    CompositeVirtualObjectRepository compositeVirtualObjectRepository;

    @Override
    public void create(CompositeVirtualObject compositeVirtualObject) {
        CompositeVirtualObjectDataObject object = CompositeVirtualObjectToDataObject(compositeVirtualObject);
        compositeVirtualObjectRepository.save(object);
    }

    @Override
    public CompositeVirtualObject retrieveByID(String id) {
        CompositeVirtualObjectDataObject object = compositeVirtualObjectRepository.findOne(id);
        return CompositeVirtualObjectDataObjectToCVO(object);
    }

    @Override
    public List<CompositeVirtualObject> retrieveByLocation(String location) {
        List<CompositeVirtualObject> cvoList = new ArrayList<>();
        List<CompositeVirtualObjectDataObject> cvoDataObjects = compositeVirtualObjectRepository.findByLocation(location);

        for(CompositeVirtualObjectDataObject cvo : cvoDataObjects){
            cvoList.add(CompositeVirtualObjectDataObjectToCVO(cvo));
        }

        return cvoList;
    }

    @Override
    public List<CompositeVirtualObject> retrieveCompositeVirtualObjectList() {
        List<CompositeVirtualObject> deviceList = new ArrayList<>();
        List<CompositeVirtualObjectDataObject> deviceDataObjects = compositeVirtualObjectRepository.findAll();

        for(CompositeVirtualObjectDataObject cvo : deviceDataObjects){
            deviceList.add(CompositeVirtualObjectDataObjectToCVO(cvo));
        }

        return deviceList;
    }

    @Override
    public void update(CompositeVirtualObject compositeVirtualObject) {
        CompositeVirtualObjectDataObject cvo = CompositeVirtualObjectToDataObject(compositeVirtualObject);
        compositeVirtualObjectRepository.insert(cvo);
    }

    @Override
    public void delete(String id) {
        compositeVirtualObjectRepository.delete(id);
    }

    private CompositeVirtualObjectDataObject CompositeVirtualObjectToDataObject(CompositeVirtualObject compositeVirtualObject){
        if(compositeVirtualObject == null) return null;
        return new CompositeVirtualObjectDataObject(compositeVirtualObject.getId(),compositeVirtualObject.getName(),compositeVirtualObject.getVoList(),compositeVirtualObject.getVoFunctionality(),compositeVirtualObject.getLocation(),compositeVirtualObject.getCreateTime(),compositeVirtualObject.getModifiedTime(),compositeVirtualObject.getDescription());
    }

    private CompositeVirtualObject CompositeVirtualObjectDataObjectToCVO(CompositeVirtualObjectDataObject dataObject){
        if(dataObject == null) return null;
        return new CompositeVirtualObject(dataObject.getId(),dataObject.getName(),dataObject.getVoList(),dataObject.getVoFunctionality(),dataObject.getLocation(),dataObject.getCreateTime(),dataObject.getModifiedTime(),dataObject.getDescription());
    }
}
