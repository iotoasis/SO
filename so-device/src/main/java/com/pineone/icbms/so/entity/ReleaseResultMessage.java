package com.pineone.icbms.so.entity;

/**
 * Created by pahnj on 2016-07-27.
 */
public class ReleaseResultMessage {

    String deviceId;
    String unregisterTime;

    public ReleaseResultMessage() {
    }

    public ReleaseResultMessage(String deviceId, String unregisterTime) {
        this.deviceId = deviceId;
        this.unregisterTime = unregisterTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUnregisterTime() {
        return unregisterTime;
    }

    public void setUnregisterTime(String unregisterTime) {
        this.unregisterTime = unregisterTime;
    }
}
