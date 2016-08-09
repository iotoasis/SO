package com.pineone.icbms.so.device.entity;

/**
 * Created by pahnj on 2016-07-27.
 */
public class ResultMessage {

    String	_commandId;
    String	_resultCode;
    String	_result;

    public ResultMessage()
    {
    }

    public ResultMessage(String _commandId, String _resultCode, String _result)
    {
        this._commandId = _commandId;
        this._resultCode = _resultCode;
        this._result = _result;
    }

    public String get_commandId()
    {
        return _commandId;
    }

    public void set_commandId(String _commandId)
    {
        this._commandId = _commandId;
    }

    public String get_resultCode()
    {
        return _resultCode;
    }

    public void set_resultCode(String _resultCode)
    {
        this._resultCode = _resultCode;
    }

    public String get_result()
    {
        return _result;
    }

    public void set_result(String _result)
    {
        this._result = _result;
    }

    // 결과 메시지가 성공인지 판단.
//    public boolean isSuccess(){
//
//        return true;
//    }

    @Override
    public String toString() {
        return "resultMessage{" +
                "_commandId='" + _commandId + '\'' +
                ", _resultCode='" + _resultCode + '\'' +
                ", _result='" + _result + '\'' +
                '}';
    }

}
