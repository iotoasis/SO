package com.pineone.icbms.so.device.entity;

/**
 * SI로 부터 전달 받는 Device 상태 DataSet
 */
public class DeviceStatusData {
    //
    /**
     * Device를 제어 했을시 제어 명령커멘드 식별자
     */
    private String          _commandId;


    /**
     * Device를 제어 했을시 디바이스 Uri
     */
    private String          _uri;

    /**
     * Device의 상태 값
     */
    private String con;

    /**
     * Device의 상태 값의 변경 되었을시 시간
     */
    private String ct;

    public DeviceStatusData() {
    }

    public DeviceStatusData(String _commandId, String _uri, String con, String ct) {
        this._commandId = _commandId;
        this._uri = _uri;
        this.con = con;
        this.ct = ct;
    }

    public String get_commandId() {
        return _commandId;
    }

    public void set_commandId(String _commandId) {
        this._commandId = _commandId;
    }

    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }


    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public boolean checkDeviceStatus(String deviceStatus){
        return this.con.equals(deviceStatus);
    }


    @Override
    public String toString() {
        return "DeviceStatusData{" +
                "_commandId='" + _commandId + '\'' +
                ", _uri='" + _uri + '\'' +
                ", con='" + con + '\'' +
                ", ct='" + ct + '\'' +
                '}';
    }
}
