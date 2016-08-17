package com.pineone.icbms.so.device.store.memory;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.store.DeviceStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceMemory implements DeviceStore {
    //
    static private Map<String,Device> deviceRepository;

    public DeviceMemory() {
        if(deviceRepository == null){
            deviceRepository = new HashMap<>();
        }
    }

    @Override
    public void create(Device device) {
        //
        deviceRepository.put(device.getDeviceId(),device);
    }

    @Override
    public Device retrieveByID(String id) {
        //
        Device device = deviceRepository.get(id);
        return device;
    }

    @Override
    public List<Device> retrievceByLocation(String location) {
        // TODO : Device 순차 검색 후 List로 변환 하여 리턴.
        List<Device> deviceList = new ArrayList<>();

        return deviceList;
    }

    @Override
    public void update(Device device) {
        //
        deviceRepository.put(device.getDeviceId(),device);
    }

    @Override
    public void delete(String id) {
        //
        deviceRepository.remove(id);
    }
}
