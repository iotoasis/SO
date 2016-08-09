package com.pineone.icbms.so.server.controller.validation;

/**
 * if Time data Doesn't useful In Occurrence controller , Occur This Exception<BR/>
 * Created by Melvin on 2015. 12. 22..
 */
public class NotUsefulTimeException extends Exception{

    public NotUsefulTimeException() {
        super("Not UseFul Time. Please Check Time information Again");
    }

    public NotUsefulTimeException(String message){
        super(message);
    }

}
