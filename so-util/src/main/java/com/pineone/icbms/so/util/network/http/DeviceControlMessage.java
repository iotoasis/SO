package com.pineone.icbms.so.util.network.http;

/**
 * SO-SI interface dataSet
 */
public class DeviceControlMessage {

    String _uri;                  // CSE relative uri
    String _notificationUri;    // Control result noti url
    String _command;             // Control the command name
    String cnf;                   // content-type and encoding
    String con;                   // data/content

    public DeviceControlMessage() {
    }

    public DeviceControlMessage(String _uri, String _notificationUri, String _command, String cnf, String con) {
        this._uri = _uri;
        this._notificationUri = _notificationUri;
        this._command = _command;
        this.cnf = cnf;
        this.con = con;
    }

    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }

    public String get_notificationUri() {
        return _notificationUri;
    }

    public void set_notificationUri(String _notificationUri) {
        this._notificationUri = _notificationUri;
    }

    public String get_command() {
        return _command;
    }

    public void set_command(String _command) {
        this._command = _command;
    }

    public String getCnf() {
        return cnf;
    }

    public void setCnf(String cnf) {
        this.cnf = cnf;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
}
