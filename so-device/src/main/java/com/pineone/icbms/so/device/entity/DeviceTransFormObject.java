package com.pineone.icbms.so.device.entity;

import java.util.List;

public class DeviceTransFormObject {

    private String          id;
    private String          deviceName;
    private String          deviceLocation;
    private String          deviceUri;
    private List<String>    deviceCommand;
    private List<String>    deviceServices;
    private long            deviceCreateTime;
    private long            deviceExfiredTime;
    private String          deviceStatus;
    private String          sessionId;

    public DeviceTransFormObject() {
    }

    public DeviceTransFormObject(String id, String deviceName, String deviceLocation, String deviceUri, List<String> deviceCommand, List<String> deviceServices, long deviceCreateTime, long deviceExfiredTime, String deviceStatus) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceCommand = deviceCommand;
        this.deviceServices = deviceServices;
        this.deviceCreateTime = deviceCreateTime;
        this.deviceExfiredTime = deviceExfiredTime;
        this.deviceStatus = deviceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getDeviceCommand() {
        return deviceCommand;
    }

    public void setDeviceCommand(List<String> deviceCommand) {
        this.deviceCommand = deviceCommand;
    }

    public List<String> getDeviceServices() {
        return deviceServices;
    }

    public void setDeviceServices(List<String> deviceServices) {
        this.deviceServices = deviceServices;
    }

    public long getDeviceCreateTime() {
        return deviceCreateTime;
    }

    public void setDeviceCreateTime(long deviceCreateTime) {
        this.deviceCreateTime = deviceCreateTime;
    }

    public long getDeviceExfiredTime() {
        return deviceExfiredTime;
    }

    public void setDeviceExfiredTime(long deviceExfiredTime) {
        this.deviceExfiredTime = deviceExfiredTime;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean checkStatus(String status){
        return this.deviceStatus.equals(status);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceLocation='" + deviceLocation + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", deviceCommand=" + deviceCommand +
                ", deviceServices=" + deviceServices +
                ", deviceCreateTime='" + deviceCreateTime + '\'' +
                ", deviceExfiredTime='" + deviceExfiredTime + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
