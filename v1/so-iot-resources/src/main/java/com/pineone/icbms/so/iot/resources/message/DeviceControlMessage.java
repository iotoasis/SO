package com.pineone.icbms.so.iot.resources.message;

/**
 * SO-SI interface dataSet<BR/>
 */
public class DeviceControlMessage {

    String _uri;                  // CSE relative uri
    String _notificationUri;    // Control result noti url
    String _commandId;          // Control the command Id
    String _command;             // Control the command name
    String cnf;                   // content-type and encoding
    String con;                   // data/content

    public DeviceControlMessage() {
    }

    public DeviceControlMessage(String _uri, String _notificationUri, String _commandId, String _command, String cnf, String con) {
        this._uri = _uri;
        this._notificationUri = _notificationUri;
        this._commandId = _commandId;
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

    public String get_commandId() {
        return _commandId;
    }

    public void set_commandId(String _commandId) {
        this._commandId = _commandId;
    }

    /**
     * {"_uri":"http://www.pineone.com/m2m/SwitchStatusSensor","_notificationUri":"http://www.pineone.com/so/resources/vdcm/VDCM-TEST-MESSAGE-001","_command":"action_ctl","cnf":"text/plain:0","con":"ON"}
     */
}
