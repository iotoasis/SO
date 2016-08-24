package com.pineone.icbms.so.servicemodel.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by melvin on 2016. 8. 23..
 */
public interface ServiceModelRepository extends MongoRepository<ServiceModelDataObject, String> {
}
