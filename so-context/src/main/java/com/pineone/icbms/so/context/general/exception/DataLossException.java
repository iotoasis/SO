package com.pineone.icbms.so.context.general.exception;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: 중요데이터 누락 예외 발생
 */
public class DataLossException extends Exception {

    public DataLossException(){
        super("Important Data Loss Exception");
    }

    public DataLossException(String message){
        super(message);
    }
}
