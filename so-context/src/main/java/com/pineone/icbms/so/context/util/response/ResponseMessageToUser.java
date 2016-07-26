package com.pineone.icbms.so.context.util.response;

import com.pineone.icbms.so.context.general.GeneralContext;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE : User 에게 전달할 메세지 생성
 */
public class ResponseMessageToUser {

    private String exceptionMessage;
    private String message;

    public static ResponseMessageToUser newResponseMessage(){
        ResponseMessageToUser responseMessage = new ResponseMessageToUser();
        return responseMessage;
    }

    // NOTE : 전달 메세지 생성
    public String generalContextResultMessage(GeneralContext generalContext){
        message = "Name : " + generalContext.getName() + " Virtual Object : " + generalContext.getDeviceObject()
                + " Concept Service" + generalContext.getConceptService() +
                " Minimum Value : " + generalContext.getMinValue() + " Maximum Value : " + generalContext.getMaxValue();
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
