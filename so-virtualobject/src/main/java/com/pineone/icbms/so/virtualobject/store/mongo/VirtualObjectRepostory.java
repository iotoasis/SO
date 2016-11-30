package com.pineone.icbms.so.virtualobject.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VirtualObjectRepostory extends MongoRepository<VirtualObjectDataObject, String> {
    List<VirtualObjectDataObject> findByvoLocation(String deviceLocation);
    List<VirtualObjectDataObject> findByvoLocationAndFunctionality(String location, String functionality);
}
