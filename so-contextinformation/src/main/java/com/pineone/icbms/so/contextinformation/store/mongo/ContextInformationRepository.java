package com.pineone.icbms.so.contextinformation.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by melvin on 2016. 8. 22..
 */
public interface ContextInformationRepository extends MongoRepository<ContextInformationDataObject, String> {
}
