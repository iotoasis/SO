package com.pineone.icbms.so.util.exception;

import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 9. 28..
 */
@Service
public class BadRequestException extends Exception {

    public BadRequestException(){
        super("Bad Request Exception");
    }

    public BadRequestException(String message){
        super(message);
    }
}
