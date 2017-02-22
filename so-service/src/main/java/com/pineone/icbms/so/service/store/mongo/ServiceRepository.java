package com.pineone.icbms.so.service.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 */
public interface ServiceRepository extends MongoRepository<ServiceDataObject, String> {

    ServiceDataObject findByName(String serviceName);
}
