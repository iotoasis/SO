package com.pineone.icbms.so.device.entity;

public class Device {
    //
    private String deviceId;
    private String deviceName;
    private String deviceLocation;
    private String deviceUri;
    private String deviceService;
    private String deviceCommand;
    private String deviceCreateTime;
    private String deviceExfiredTime;


    public Device() {
    }

    public Device(String deviceId, String deviceName, String deviceLocation, String deviceUri, String deviceService, String deviceCommand, String deviceCreateTime, String deviceExfiredTime) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceService = deviceService;
        this.deviceCommand = deviceCommand;
        this.deviceCreateTime = deviceCreateTime;
        this.deviceExfiredTime = deviceExfiredTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public String getDeviceService() {
        return deviceService;
    }

    public void setDeviceService(String deviceService) {
        this.deviceService = deviceService;
    }

    public String getDeviceCommand() {
        return deviceCommand;
    }

    public void setDeviceCommand(String deviceCommand) {
        this.deviceCommand = deviceCommand;
    }

    public String getDeviceCreateTime() {
        return deviceCreateTime;
    }

    public void setDeviceCreateTime(String deviceCreateTime) {
        this.deviceCreateTime = deviceCreateTime;
    }

    public String getDeviceExfiredTime() {
        return deviceExfiredTime;
    }

    public void setDeviceExfiredTime(String deviceExfiredTime) {
        this.deviceExfiredTime = deviceExfiredTime;
    }
}
