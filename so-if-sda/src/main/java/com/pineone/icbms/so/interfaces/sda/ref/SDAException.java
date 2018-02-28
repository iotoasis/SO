package com.pineone.icbms.so.interfaces.sda.ref;

/**
 * Created by melvin on 2017. 4. 24..
 */
public class SDAException extends Exception{

	private static final long serialVersionUID = -6591909415556966767L;

	public SDAException(){
        super("SDA Exception.");
    }

    public SDAException(String message){
        super(message);
    }
}
