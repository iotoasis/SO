package com.pineone.icbms.so.entity;

/**
 * Created by pahnj on 2016-07-13.
 */
public class DeviceModel {
    //
    private String deviceId;
    private String deviceName;
    private String deviceModelName;
    private String deviceUri;
    private String functionality;
    private String aspect;

    public DeviceModel() {
    }

    public DeviceModel(String deviceId, String deviceName, String deviceModelName, String deviceUri,String functionality,String aspect) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceModelName = deviceModelName;
        this.deviceUri = deviceUri;
        this.functionality = functionality;
        this.aspect = aspect;
    }

    public static DeviceModel newInstance(String deviceId, String deviceName, String deviceModelName, String deviceCommand, String deviceUri,String functionality,String aspect) {
        DeviceModel deviceModel = new DeviceModel(deviceId,deviceName,deviceModelName,deviceUri,functionality,aspect);
        return deviceModel;
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

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceModelName='" + deviceModelName + '\'' +
                ", deviceUri='" + deviceUri + '\'' +
                ", functionality='" + functionality + '\'' +
                ", aspect='" + aspect + '\'' +
                '}';
    }
}
