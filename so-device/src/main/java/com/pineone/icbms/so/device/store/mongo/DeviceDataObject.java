package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Device")
public class DeviceDataObject {

    private String          deviceId;
    private String          deviceName;
    private String          deviceLocation;
    private String          deviceUri;
    private List<String> deviceCommand;
    private List<String>    deviceServices;
    private String          deviceCreateTime;
    private String          deviceExfiredTime;

    public DeviceDataObject() {
    }

    public DeviceDataObject(String deviceId, String deviceName, String deviceLocation, String deviceUri, List<String> deviceCommand, List<String> deviceServices, String deviceCreateTime, String deviceExfiredTime) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceCommand = deviceCommand;
        this.deviceServices = deviceServices;
        this.deviceCreateTime = deviceCreateTime;
        this.deviceExfiredTime = deviceExfiredTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public List<String> getDeviceCommand() {
        return deviceCommand;
    }

    public List<String> getDeviceServices() {
        return deviceServices;
    }

    public String getDeviceCreateTime() {
        return deviceCreateTime;
    }

    public String getDeviceExfiredTime() {
        return deviceExfiredTime;
    }
}
