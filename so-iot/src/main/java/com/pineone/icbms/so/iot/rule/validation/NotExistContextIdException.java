package com.pineone.icbms.so.iot.rule.validation;

/**
 * Created by Melvin on 2015. 12. 24..
 */
public class NotExistContextIdException extends Exception{

    public NotExistContextIdException() {
        super("NotExist ContextModelId Exception");
    }

    public NotExistContextIdException(String message){
        super(message);
    }

}
