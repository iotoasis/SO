package com.pineone.icbms.so.servicemodel.ref;

import com.pineone.icbms.so.servicemodel.entity.ServiceModel;

public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE : ServiceModel 전달 메시지 생성
    public String serviceModelResultMessage(ServiceModel serviceModel){
        message = " Name : " + serviceModel.getName();
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
