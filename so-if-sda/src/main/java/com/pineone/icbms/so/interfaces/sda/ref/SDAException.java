package com.pineone.icbms.so.interfaces.sda.ref;

/**
 * Created by melvin on 2017. 4. 24..
 */
public class SDAException extends Exception{

    public SDAException(){
        super("SDA Exception.");
    }

    public SDAException(String message){
        super(message);
    }
}
