package com.pineone.icbms.so.server.controller.validation;

/**
 * Created by Melvin on 2015. 12. 22..
 */
public class NotUsefulTimeException extends Exception{

    public NotUsefulTimeException() {
        super("Not UseFul Time Getting Exception. Please Check Time information Again");
    }

    public NotUsefulTimeException(String message){
        super(message);
    }

}
