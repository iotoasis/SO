package com.pineone.icbms.so.device.store;

import com.pineone.icbms.so.device.entity.DeviceResult;

public interface DeviceResultStore {

    void create(DeviceResult deviceResult);
    DeviceResult retrieve(String id);

    void update(DeviceResult deviceResult);
    void delete(String id);

}
