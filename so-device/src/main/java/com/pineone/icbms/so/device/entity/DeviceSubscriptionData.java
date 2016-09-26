package com.pineone.icbms.so.device.entity;

public class DeviceSubscriptionData {
    //
    private String      _uri;
    private String      _command;
    private String      _notificationuri;

    public DeviceSubscriptionData() {
    }

    public DeviceSubscriptionData(String _uri, String _command, String _notificationuri) {
        this._uri = _uri;
        this._command = _command;
        this._notificationuri = _notificationuri;
    }

    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }

    public String get_command() {
        return _command;
    }

    public void set_command(String _command) {
        this._command = _command;
    }

    public String get_notificationuri() {
        return _notificationuri;
    }

    public void set_notificationuri(String _notificationuri) {
        this._notificationuri = _notificationuri;
    }

    @Override
    public String toString() {
        return "DeviceSubscriptionData{" +
                "_uri='" + _uri + '\'' +
                ", _command='" + _command + '\'' +
                ", _notificationuri='" + _notificationuri + '\'' +
                '}';
    }
}
