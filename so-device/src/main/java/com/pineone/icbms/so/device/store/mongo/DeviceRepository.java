package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceRepository extends MongoRepository<DeviceDataObject, String> {

    List<DeviceDataObject> findBydeviceLocation(String deviceLocation);
    DeviceDataObject findBydeviceId(String deviceId);

}
