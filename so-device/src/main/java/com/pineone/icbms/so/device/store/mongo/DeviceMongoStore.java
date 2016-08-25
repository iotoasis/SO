package com.pineone.icbms.so.device.store.mongo;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.store.DeviceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceMongoStore implements DeviceStore{

    @Autowired
    DeviceRepository deviceRepository;

//    private static DeviceMongoStore instance;
//
//    public DeviceMongoStore() {
//    }

//    public static DeviceMongoStore getInstance(){
//        if(instance == null) {
//            instance = new DeviceMongoStore();
//        }
//        return instance;
//    }


    @Override
    public void create(Device device) {
        DeviceDataObject d = deviceToDataObject(device);
        deviceRepository.save(d);
    }

    @Override
    public Device retrieveByID(String id) {
        DeviceDataObject d = deviceRepository.findBydeviceId(id);
        return DeviceObjectToDevice(d);
    }

    @Override
    public List<Device> retrieveByLocation(String deviceLocation) {
        List<Device> deviceList = new ArrayList<>();
        List<DeviceDataObject> deviceDataObjects = deviceRepository.findBydeviceLocation(deviceLocation);

        for(DeviceDataObject d : deviceDataObjects){
            deviceList.add(DeviceObjectToDevice(d));
        }

        return deviceList;
    }

    @Override
    public List<String> retrieveDeviceService(String location) {
        // Device들을 가지고 온 뒤 1차 가공해서 Service만 도출하여 보내줌.
        // 아니면 다른걸 보내줌.
        // TODO :
        return null;
    }

    @Override
    public List<Device> retrieveDeviceList() {
        List<Device> deviceList = new ArrayList<>();
        List<DeviceDataObject> deviceDataObjects = deviceRepository.findAll();

        for(DeviceDataObject d : deviceDataObjects){
            deviceList.add(DeviceObjectToDevice(d));
        }

        return deviceList;
    }

    @Override
    public void update(Device device) {
        DeviceDataObject d = deviceToDataObject(device);
        deviceRepository.insert(d);
    }

    @Override
    public void delete(String id) {
        deviceRepository.delete(id);
    }


    private DeviceDataObject deviceToDataObject(Device device){
        if(device == null) return null;
        return new DeviceDataObject(device.getDeviceId(),device.getDeviceName(),device.getDeviceLocation(),device.getDeviceUri(),device.getDeviceCommand(),device.getDeviceServices(),device.getDeviceCreateTime(),device.getDeviceExfiredTime());
    }

    private Device DeviceObjectToDevice(DeviceDataObject deviceDataObject) {
        if(deviceDataObject == null) return null;
        return new Device(deviceDataObject.getDeviceId(),deviceDataObject.getDeviceName(),deviceDataObject.getDeviceLocation(),deviceDataObject.getDeviceUri(),deviceDataObject.getDeviceCommand(),deviceDataObject.getDeviceServices(),deviceDataObject.getDeviceCreateTime(),deviceDataObject.getDeviceExfiredTime());
    }

}
