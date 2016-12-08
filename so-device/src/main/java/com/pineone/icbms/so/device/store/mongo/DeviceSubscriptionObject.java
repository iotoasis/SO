package com.pineone.icbms.so.device.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Device Subscription에 대한 데이터
 */
@Document(collection = "DeviceSubscription")
public class DeviceSubscriptionObject {
    //

    private String _id;

    private String _commandId;

    private String deviceStatus;

    public DeviceSubscriptionObject() {
    }

    public DeviceSubscriptionObject(String _commandId, String deviceStatus) {
        this._commandId = _commandId;
        this.deviceStatus = deviceStatus;
    }

    public DeviceSubscriptionObject(String _id, String _commandId, String deviceStatus) {
        this._id = _id;
        this._commandId = _commandId;
        this.deviceStatus = deviceStatus;
    }

    public String get_commandId() {
        return _commandId;
    }

    public void set_commandId(String _commandId) {
        this._commandId = _commandId;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "DeviceSubscriptionObject{" +
                "_id='" + _id + '\'' +
                ", _commandId='" + _commandId + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
