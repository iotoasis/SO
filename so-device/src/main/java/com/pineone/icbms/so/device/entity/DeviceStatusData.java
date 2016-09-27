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
    private String          status;

    /**
     * Device의 상태 값의 변경 되었을시 시간
     */
    private String          timestamp;

    public DeviceStatusData() {
    }

    public DeviceStatusData(String _commandId, String _uri, String _command, String status, String timestamp) {
        this._commandId = _commandId;
        this._uri = _uri;
        this._command = _command;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean checkDeviceStatus(String deviceStatus){
        return this.status.equals(deviceStatus);
    }


    @Override
    public String toString() {
        return "DeviceStatusData{" +
                "_commandId='" + _commandId + '\'' +
                ", _uri='" + _uri + '\'' +
                ", _command='" + _command + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
