package com.pineone.icbms.so.server.controller.validation;

/**
 * if Time doesn't Exist In Occurrence controller , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 22..
 */
public class NotExistTimeException extends Exception{

    public NotExistTimeException() {
        super("NotExist Time Exception");
    }

    public NotExistTimeException(String message){
        super(message);
    }
}
