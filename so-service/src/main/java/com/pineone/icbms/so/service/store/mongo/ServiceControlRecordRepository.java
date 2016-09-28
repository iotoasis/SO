package com.pineone.icbms.so.service.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceControlRecordRepository extends MongoRepository<ServiceControlRecordDataObject, String> {
}
