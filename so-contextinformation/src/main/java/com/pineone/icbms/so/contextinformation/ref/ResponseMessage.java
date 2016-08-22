package com.pineone.icbms.so.contextinformation.ref;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;

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

    // NOTE : ContextInformationLogic 전달 메세지 생성
    public String contextInformationResultMessage(ContextInformation contextInformation){
        message = "ID: " + contextInformation.getId() + ", Name : " + contextInformation.getName() + ", Virtual Object : "
                + contextInformation.getDeviceObjectName() + ", Concept Service" + contextInformation.getConceptServiceName() +
                ", Minimum Value : " + contextInformation.getMinValue() + ", Maximum Value : " + contextInformation.getMaxValue();
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
