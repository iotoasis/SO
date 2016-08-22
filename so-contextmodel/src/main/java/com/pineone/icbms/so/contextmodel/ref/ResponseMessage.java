package com.pineone.icbms.so.contextmodel.ref;

import com.pineone.icbms.so.contextmodel.entity.ContextModel;

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

    // NOTE : contextModel 전달 메시지 생성
    public String contextModelResultMessage(ContextModel contextModel){
        message = " Name : " + contextModel.getName() + " ContextType : " + contextModel.getContextType();
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
