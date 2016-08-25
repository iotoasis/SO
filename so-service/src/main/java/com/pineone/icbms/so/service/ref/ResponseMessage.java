package com.pineone.icbms.so.service.ref;

import com.pineone.icbms.so.service.entity.Service;

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

    //NOTE : Service 전달 메시지 생성
    public String serviceResultMessage(Service service){
        message = "ID: " + service.getId() + ", Name : " + service.getName() + ", Virtual Object : "
                + service.getVirtualObjectId() + ", Concept Service" + service.getVirtualObjectService() +
                ", Status : " + service.getStatus();
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
