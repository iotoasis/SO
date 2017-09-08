package com.pineone.icbms.so.web.interfaces.api.service.context;

/**
 * Created by melvin on 2016. 8. 9..
 */
//NOTE: SO 에서 리턴해주는 메세지 형태 제공
public class ResponseMessage {

    private String exceptionMessage;
    private String message;

    public static ResponseMessage newResponseMessage(){
        ResponseMessage responseMessage = new ResponseMessage();
        return responseMessage;
    }

    // NOTE : contextModel 전달 메시지 생성
    public String contextModelResultMessage(ContextModel contextModel){
        message = " Id : " + contextModel.getId() + " time: " + contextModel.getOccTime();
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
