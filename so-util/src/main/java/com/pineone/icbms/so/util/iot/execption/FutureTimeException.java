package com.pineone.icbms.so.util.iot.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Melvin on 2015. 11. 5..
 *
 */

public class FutureTimeException extends Exception{

    Logger logger = LoggerFactory.getLogger(FutureTimeException.class);

    @Override
    public String getMessage() {
        logger.info("> FutureTimeException");

        String msg = "FutureTimeException";

        return msg;
    }







}
