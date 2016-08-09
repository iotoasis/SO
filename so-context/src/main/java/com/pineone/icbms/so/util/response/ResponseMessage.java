package com.pineone.icbms.so.util.response;

import com.pineone.icbms.so.contextinformation.entity.ContextInformation;
import com.pineone.icbms.so.contextmodel.entity.ContextModel;
import com.pineone.icbms.so.service.entity.Service;

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

    // NOTE : ContextInformationLogic 전달 메세지 생성
    public String contextInformationResultMessage(ContextInformation contextInformation){
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

    // NOTE: BizContext 전달 메시지 생성
    public String bizContextResultMessage(String bizContextName) {
        message = " Name : " + bizContextName ;
        return message;
    }

    //NOTE : Service 전달 메시지 생성
    public String serviceResultMessage(Service service){
        message = "ID: " + service.getId() + ", Name : " + service.getName() + ", Virtual Object : "
                + service.getDeviceObject() + ", Concept Service" + service.getConceptService() +
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
