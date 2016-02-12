package com.pineone.icbms.so.interfaces.sda.client;

/**
 * Created by Melvin on 2016. 1. 19..
 */
public class NotExistDataException extends Exception{

    public NotExistDataException() {
        super("NotExist Data Exception");
    }

    public NotExistDataException(String message){
        super(message);
    }

}
