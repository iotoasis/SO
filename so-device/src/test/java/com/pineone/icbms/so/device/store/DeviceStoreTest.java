package com.pineone.icbms.so.device.store;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.store.mongo.DeviceMongoStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeviceStoreTest {

    private DeviceStore deviceStore = new DeviceMongoStore();

    @Test
    public void setup(){
        Device device = new Device();
        device.setDeviceId("/herit-in/herit-cse/ONDB_AirCleaner01_001");
        device.setDeviceCreateTime("201608311301");
        device.setDeviceExfiredTime("201708311301");
        device.setDeviceLocation("ITBT_606_HUB03");
        device.setDeviceName("ONDB_AirCleaner01_001");
        List<String> deviceServices = new ArrayList<>();
        List<String> deviceCommands = new ArrayList<>();
        deviceServices.add("AirCleaner_PowerControl_ON");
        deviceServices.add("AirCleaner_PowerControl_OFF");
        deviceCommands.add("ON");
        deviceCommands.add("OFF");
        device.setDeviceServices(deviceServices);
        device.setDeviceCommand(deviceCommands);
        device.setDeviceUri("/herit-in/herit-cse/ONDB_AirCleaner01_001");

        deviceStore.create(device);


    }
}
