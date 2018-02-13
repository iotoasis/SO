package com.pineone.icbms.so.interfaces.sda.ref;

/**
 * Created by melvin on 2017. 4. 24..
 */
public class DataNotExistException extends SDAException {

	private static final long serialVersionUID = 1L;

	public DataNotExistException(){
        super("DataNotExist Exception.");
    }
}
