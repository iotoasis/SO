package com.pineone.icbms.so.device.store.memory;

import com.pineone.icbms.so.device.entity.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceMemory{
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

    public void create(Device device) {
        //
        deviceRepository.put(device.getDeviceId(),device);
    }

    public Device retrieveByID(String id) {
        //
        Device device = deviceRepository.get(id);
        return device;
    }

    public List<Device> retrieveByLocation(String location) {
        // TODO : Device DB 적용후 적용
        List<Device> deviceList = new ArrayList<>();

        return deviceList;
    }

    public List<String> retrieveDeviceService(String location) {
        // TODO : Device DB 적용후 적용
        List<String> deviceServiceList = new ArrayList<>();
        return deviceServiceList;
    }

    public void update(Device device) {
        //
        deviceRepository.put(device.getDeviceId(),device);
    }

    public void delete(String id) {
        //
        deviceRepository.remove(id);
    }
}
