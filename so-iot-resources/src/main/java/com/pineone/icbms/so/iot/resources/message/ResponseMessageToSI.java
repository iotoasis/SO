package com.pineone.icbms.so.iot.resources.message;

/**
 * Created by use on 2015-11-04.
 */
public class ResponseMessageToSI {

    String code;
    String _commandId;

    public ResponseMessageToSI() {
    }

    public ResponseMessageToSI(String code, String _commandId) {
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
}
