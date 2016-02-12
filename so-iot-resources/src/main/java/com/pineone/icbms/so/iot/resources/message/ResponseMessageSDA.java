package com.pineone.icbms.so.iot.resources.message;

/**
 * Created by Melvin on 2015. 12. 28..
 */
public class ResponseMessageSDA {

    String	code;
    String	message;
    String	content;
    String  occResult;

    public ResponseMessageSDA()
    {
    }

    public ResponseMessageSDA(String code, String message, String content)
    {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }


    public String getOccResult() {
        return occResult;
    }

    public void setOccResult(String occResult) {
        this.occResult = occResult;
    }
}
