package com.pineone.icbms.so.store;

import com.pineone.icbms.so.entity.DeviceResult;

public interface DeviceResultStore {

    void create(DeviceResult deviceResult);
    DeviceResult retrieve(String id);

    void update(DeviceResult deviceResult);
    void delete(String id);

}
