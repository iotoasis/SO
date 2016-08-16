package com.pineone.icbms.so.profile.ref;

import com.pineone.icbms.so.profile.entity.Profile;

/**
 * Created by melvin on 2016. 8. 9..
 */
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE : ServiceModel 전달 메시지 생성
    public String profileResultMessage(Profile profile){
        message = " Name : " + profile.getName() + ", ContextModelName : " + profile.getContextModelName() +
                ", ServiceModelName : " + profile.getServiceModelName();
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
