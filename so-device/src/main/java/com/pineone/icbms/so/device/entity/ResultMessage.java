package com.pineone.icbms.so.device.entity;

public class ResultMessage {

    private String code;
    private String _commandId;

    public ResultMessage() {
    }

    public ResultMessage(String code, String _commandId) {
        this.code = code;
        this._commandId = _commandId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String get_commandId() {
        return _commandId;
    }

    public void set_commandId(String _commandId) {
        this._commandId = _commandId;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "code='" + code + '\'' +
                ", _commandId='" + _commandId + '\'' +
                '}';
    }
}
