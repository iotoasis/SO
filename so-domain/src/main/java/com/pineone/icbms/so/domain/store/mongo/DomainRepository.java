package com.pineone.icbms.so.domain.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by melvin on 2016. 8. 22..
 */
public interface DomainRepository extends MongoRepository<DomainDataObject, String> {

}
