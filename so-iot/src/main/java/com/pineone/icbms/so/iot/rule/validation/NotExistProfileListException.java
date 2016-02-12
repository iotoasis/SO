package com.pineone.icbms.so.iot.rule.validation;

/**
 * Created by Melvin on 2015. 12. 24..
 */
public class NotExistProfileListException extends Exception {

    public NotExistProfileListException() {
        super("NotExist Profile Exception");
    }

    public NotExistProfileListException(String message){
        super(message);
    }
}
