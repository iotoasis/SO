package com.pineone.icbms.so.interfaces.database.ref;


import com.pineone.icbms.so.interfaces.database.model.VirtualObjectForDB;
import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 9..
 */

@Service
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    //   VirtualObject 관련 전달 메시지 생성
    public String virtualObjectResultMessage(VirtualObjectForDB virtualObjectForDB) {
        message = virtualObjectForDB.toString();
        return message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}
