package com.pineone.icbms.so.entity;

/**
 * Created by pahnj on 2016-07-13.
 */
public class Device {
    //
    private DeviceModel deviceModel;
    private String operation;
    private String location;

    public Device() {
    }

    public Device(DeviceModel deviceModel,String location) {
        this.deviceModel = deviceModel;
        this.location = location;
    }

    public static Device newInstance(DeviceModel deviceModel,String location){
        Device device = new Device(deviceModel,location);
        return device;
    }

    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
