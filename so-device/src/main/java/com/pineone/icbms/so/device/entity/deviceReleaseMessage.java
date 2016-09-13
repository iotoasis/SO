package com.pineone.icbms.so.device.entity;

public class deviceReleaseMessage {

    String deviceId;
    String registerTime;

    public deviceReleaseMessage() {
    }

    public deviceReleaseMessage(String deviceId, String registerTime) {
        this.deviceId = deviceId;
        this.registerTime = registerTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "deviceReleaseMessage{" +
                "deviceId='" + deviceId + '\'' +
                ", registerTime='" + registerTime + '\'' +
                '}';
    }
}
