package com.pineone.icbms.so.device.entity;

public class DeviceTransFormObject {

    private String          deviceId;
    private String          deviceName;
    private String          deviceLocation;
    private String          deviceUri;
    private String          deviceCommand;
    private String          deviceServices;
    private String          status;

    public DeviceTransFormObject() {
    }

    public DeviceTransFormObject(String deviceId, String deviceName, String deviceLocation, String deviceUri, String deviceCommand, String deviceServices, String status) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceUri = deviceUri;
        this.deviceCommand = deviceCommand;
        this.deviceServices = deviceServices;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeviceTransFormObject{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceLocation='" + deviceLocation + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", deviceCommand='" + deviceCommand + '\'' +
                ", deviceServices='" + deviceServices + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
