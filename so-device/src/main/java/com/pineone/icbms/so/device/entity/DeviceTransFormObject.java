package com.pineone.icbms.so.device.entity;

public class DeviceTransFormObject {

    private String id;
    private String          deviceName;
    private String          deviceLocation;
    private String          deviceUri;
    private String          deviceCommand;
    private String          deviceServices;
    private String deviceStatus;
    private String          sessionId;

    public DeviceTransFormObject() {
    }

    public DeviceTransFormObject(String id, String deviceName, String deviceLocation, String deviceUri, String deviceCommand, String deviceServices, String deviceStatus, String sessionId) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceCommand = deviceCommand;
        this.deviceServices = deviceServices;
        this.deviceStatus = deviceStatus;
        this.sessionId = sessionId;
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

    public String getDeviceCommand() {
        return deviceCommand;
    }

    public void setDeviceCommand(String deviceCommand) {
        this.deviceCommand = deviceCommand;
    }

    public String getDeviceServices() {
        return deviceServices;
    }

    public void setDeviceServices(String deviceServices) {
        this.deviceServices = deviceServices;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "DeviceTransFormObject{" +
                "id='" + id + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceLocation='" + deviceLocation + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", deviceCommand='" + deviceCommand + '\'' +
                ", deviceServices='" + deviceServices + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
