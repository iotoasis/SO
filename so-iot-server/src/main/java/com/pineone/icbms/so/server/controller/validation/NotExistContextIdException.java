package com.pineone.icbms.so.server.controller.validation;

/**
 * if Context Id doesn't Exist In Occurrence controller , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 22..
 */
public class NotExistContextIdException extends Exception {

    public NotExistContextIdException() {
        super("NotExist ContextId Exception");
    }

    public NotExistContextIdException(String message){
        super(message);
    }
}
