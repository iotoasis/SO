package com.pineone.icbms.so.device.store.memory;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.store.DeviceStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceMemory implements DeviceStore {
    //
    private Map<String,Device> deviceRepository = new HashMap<>();

    private static DeviceMemory instance;

    private DeviceMemory() {
    }

    public static DeviceMemory getInstance(){
        if(instance == null){
            instance = new DeviceMemory();
        }
        return instance;
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
    public List<Device> retrieveByLocation(String location) {
        // TODO : Device DB 적용후 적ㅇㅇ
        List<Device> deviceList = new ArrayList<>();

        return deviceList;
    }

    @Override
    public List<String> retrieveDeviceService(String location) {
        // TODO : Device DB 적용후 적ㅇㅇ
        List<String> deviceServiceList = new ArrayList<>();
        return deviceServiceList;
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
