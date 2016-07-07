package com.pineone.icbms.so.interfaces.sda.client;

/**
 * Created by Melvin on 2016. 1. 21..
 */
public class NotExistDomainTypeException extends RuntimeException {

    public NotExistDomainTypeException() {
        super("NotExist DomainType Exception");
    }

    public NotExistDomainTypeException(String message){
        super(message);
    }
}
