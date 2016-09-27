package com.pineone.icbms.so.device.entity;

/**
 * SI -> SO Device가 등록되었다는 noti에 대한 DataSet
 */
public class deviceReleaseMessage {

    /**
     * Release된 Device의 식별자     ex) /herit-in/herit-cse/ONDB_BeamProjector01_001
     */
    String deviceId;

    /**
     * Release된 Device의 시간      ex) 201608311301
     */
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
