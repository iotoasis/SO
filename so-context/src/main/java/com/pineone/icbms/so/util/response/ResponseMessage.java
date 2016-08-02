package com.pineone.icbms.so.util.response;

import com.pineone.icbms.so.context_information.entity.ContextInformation;
import com.pineone.icbms.so.context_model.entity.ContextModel;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE : User 에게 전달할 메세지 생성
 */
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE : ContextInformation 전달 메세지 생성
    public String generalContextResultMessage(ContextInformation contextInformation){
        message = "ID: " + contextInformation.getId() + ", Name : " + contextInformation.getName() + ", Virtual Object : "
                + contextInformation.getDeviceObject() + ", Concept Service" + contextInformation.getConceptService() +
                ", Minimum Value : " + contextInformation.getMinValue() + ", Maximum Value : " + contextInformation.getMaxValue();
        return message;
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
