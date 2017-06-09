package com.pineone.icbms.so.interfaces.si.model;

/**
 * Created by melvin on 2017. 4. 4..
 */

//SO - SI interface dataSet
public class DeviceControlMessage {

    //CSE Relative URI                            ex) /herit-in/herit-cse/ONDB_BeamProjector01_001
    String _uri;

    //Device 를 제어할 Command 의 ID                 ex) cmd_20160927001
    String _commandId;

    //디바이스 제어 내용                               ex) Power
    String _command;

    //Device 제어시 컨텐츠 타입 및 인코딩 타입             ex) text/plain:0
    String cnf;

    //Device 제어 명령어.                           ex) ON
    String con;

    public DeviceControlMessage() {
    }

    public DeviceControlMessage(String _uri, String _commandId, String _command, String cnf, String con) {
        this._uri = _uri;
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

    public String get_commandId() {
        return _commandId;
    }

    public void set_commandId(String _commandId) {
        this._commandId = _commandId;
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

    @Override
    public String toString() {
        return "DeviceControlMessage{" +
                "_uri='" + _uri + '\'' +
                ", _commandId='" + _commandId + '\'' +
                ", _command='" + _command + '\'' +
                ", cnf='" + cnf + '\'' +
                ", con='" + con + '\'' +
                '}';
    }

    /**
     * {"_uri":"/herit-in/herit-cse/Siren_LB0001SR0001","_commandId":"VDCM-TEST-MESSAGE-120301","_command":"action","cnf":"text/plain:0","con":"SIREN"}
     */
}

