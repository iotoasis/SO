package com.pineone.icbms.so.interfaces.database.ref;

import org.springframework.stereotype.Service;

/**
 * 중요데이터 누락 예외 발생
 */
@Service
public class DataLossException extends Exception {

    public DataLossException(){
        super("Important Data Loss Exception");
    }

    public DataLossException(String message){
        super(message);
    }
}
