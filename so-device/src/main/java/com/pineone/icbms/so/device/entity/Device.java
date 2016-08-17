package com.pineone.icbms.so.device.entity;

import java.util.List;

public class Device {

    // Command Device Parameter
    private String          deviceId;
    private String          deviceName;
    private String          deviceLocation;
    private String          deviceUri;
    private List<String>    deviceCommand;
    private List<String>    deviceServices;
    private String          deviceCreateTime;
    private String          deviceExfiredTime;

    // Real Device Parameter
    private String          deviceRealCommandId;
    private String          deviceRealService;
    private String          deviceRealCommand;

    public Device() {
    }

    public Device(String deviceId, String deviceName, String deviceLocation, String deviceUri, List<String> deviceServices, List<String> deviceCommand, String deviceCreateTime, String deviceExfiredTime) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceServices = deviceServices;
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

    public List<String> getDeviceServices() {
        return deviceServices;
    }

    public void setDeviceServices(List<String> deviceServices) {
        this.deviceServices = deviceServices;
    }

    public List<String> getDeviceCommand() {
        return deviceCommand;
    }

    public void setDeviceCommand(List<String> deviceCommand) {
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

    public String getDeviceRealService() {
        return deviceRealService;
    }

    public void setDeviceRealService(String deviceRealService) {
        this.deviceRealService = deviceRealService;
    }

    public String getDeviceRealCommand() {
        return deviceRealCommand;
    }

    public void setDeviceRealCommand(String deviceRealCommand) {
        this.deviceRealCommand = deviceRealCommand;
    }

    public String getDeviceRealCommandId() {
        return deviceRealCommandId;
    }

    public void setDeviceRealCommandId(String deviceRealCommandId) {
        this.deviceRealCommandId = deviceRealCommandId;
    }
}
