package com.pineone.icbms.so.compositevo.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompositeVirtualObjectRepository extends MongoRepository<CompositeVirtualObjectDataObject, String> {
    List<CompositeVirtualObjectDataObject> findByLocation(String location);

}


