package com.pineone.icbms.so.server.controller.validation;

/**
 * if Domain doesn't Exist In Occurrence controller , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 22..
 */
public class NotExistDomainException extends Exception{

    public NotExistDomainException() {
        super("NotExist Domain Information");
    }

    public NotExistDomainException(String message){
        super(message);
    }
}
