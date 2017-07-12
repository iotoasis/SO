package com.pineone.icbms.so.interfaces.si.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class DeviceSubscriptionData {
    @Getter @Setter
    private String      _uri;
    @Getter @Setter
    private String      _commandId;

    @Getter @Setter
    private String      _notificationUri;

    public DeviceSubscriptionData() {
    }

    public DeviceSubscriptionData(String _uri, String _commandId, String _notificationUri) {
        this._uri = _uri;
        this._commandId = _commandId;
        this._notificationUri = _notificationUri;
    }

//    public String get_uri() {
//        return _uri;
//    }

//    public void set_uri(String _uri) {
//        this._uri = _uri;
//    }
//
//    public String get_commandId() {
//        return _commandId;
//    }
//
//    public void set_commandId(String _commandId) {
//        this._commandId = _commandId;
//    }
//
//    public String get_notificationUri() {
//        return _notificationUri;
//    }
//
//    public void set_notificationUri(String _notificationUri) {
//        this._notificationUri = _notificationUri;
//    }
//
//    @Override
//    public String toString() {
//        return "DeviceSubscriptionData{" +
//                "_uri='" + _uri + '\'' +
//                ", _commandId='" + _commandId + '\'' +
//                ", _notificationUri='" + _notificationUri + '\'' +
//                '}';
//    }
}
