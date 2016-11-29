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
     * Device를 제어 했을시 제어 항목
     */
    private String          _command;

    /**
     * Device의 상태 값
     */
    private String deviceStatus;

    /**
     * Device의 상태 값의 변경 되었을시 시간
     */
    private String          timestamp;

    public DeviceStatusData() {
    }

    public DeviceStatusData(String _commandId, String _uri, String _command, String deviceStatus, String timestamp) {
        this._commandId = _commandId;
        this._uri = _uri;
        this._command = _command;
        this.deviceStatus = deviceStatus;
        this.timestamp = timestamp;
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

    public String get_command() {
        return _command;
    }

    public void set_command(String _command) {
        this._command = _command;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean checkDeviceStatus(String deviceStatus){
        return this.deviceStatus.equals(deviceStatus);
    }


    @Override
    public String toString() {
        return "DeviceStatusData{" +
                "_commandId='" + _commandId + '\'' +
                ", _uri='" + _uri + '\'' +
                ", _command='" + _command + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
