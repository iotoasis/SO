package com.pineone.icbms.so.util.exception;

import org.springframework.stereotype.Service;

/**
 * Created by melvin on 2016. 7. 13..
 * NOTE: 중요데이터 누락 예외 발생
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
