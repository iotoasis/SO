package com.pineone.icbms.so.contextmodel.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by melvin on 2016. 8. 22..
 */
public interface ContextModelRepository extends MongoRepository<ContextModelDataObject, String> {

    ContextModelDataObject findByname(String name);

}
