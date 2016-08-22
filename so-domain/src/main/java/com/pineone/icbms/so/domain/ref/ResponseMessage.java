package com.pineone.icbms.so.domain.ref;

import com.pineone.icbms.so.domain.entity.Domain;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE : Domain 등록 관련 Response 메세지
 */
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE : Domain 생성, 전달 메세지 생성
    public String domainResultMessage(Domain domain){
        message = "ID: " + domain.getId() + ", Name : " + domain.getName() + ", URI: " + domain.getUri();
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
