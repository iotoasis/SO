package com.pineone.icbms.so.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by existmaster on 2016. 9. 1..
 */
public class SampleLogger {

    public static final Logger logger = LoggerFactory.getLogger(SampleLogger.class);

    public void function() {
        logger.trace("This is trace.");
        logger.debug("This is debug.");
        logger.info("This is info.");
        logger.warn("This is warn.");
        logger.error("This is error.");
    }
}
