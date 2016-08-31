package com.pineone.icbms.so.device.store;

import com.pineone.icbms.so.device.entity.Device;

import java.util.List;

public interface DeviceStore {
    void create(Device device);
    Device retrieveByID(String id);
    List<Device> retrieveByLocation(String location);
    List<String> retrieveDeviceService(String location);
    List<Device> retrieveDeviceList();
    void update(Device device);
    void delete(String id);
}
