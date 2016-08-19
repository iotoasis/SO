package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceResultRepository extends MongoRepository<DeviceResultDataObject, String> {

}
