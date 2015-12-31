package com.pineone.icbms.so.util.iot.execption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Melvin on 2015. 11. 11..
 */
public class NullPointException extends Exception {

    Logger logger = LoggerFactory.getLogger(NullPointException.class);

    @Override
    public String getMessage() {
        logger.info("> NullPointException");

        String msg = "NullPointException";

        return msg;
    }
}
