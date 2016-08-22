package com.pineone.icbms.so.bizcontext.ref;

import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 8. 9..
 */

@Service
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE: BizContext 전달 메시지 생성
    public String bizContextResultMessage(String bizContextName) {
        message = " Name : " + bizContextName ;
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
