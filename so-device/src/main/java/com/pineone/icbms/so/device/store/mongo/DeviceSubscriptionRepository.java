package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceSubscriptionRepository extends MongoRepository<DeviceSubscriptionObject, String> {

    DeviceSubscriptionObject findBy_commandId(String commandId);

}
