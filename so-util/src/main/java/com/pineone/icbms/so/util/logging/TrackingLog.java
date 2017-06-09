package com.pineone.icbms.so.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logging for service tracking.<BR/>
 *
 * Created by uni4love on 2017. 1. 14..
 */
public class TrackingLog {
    /**
     * logger
     */
    private static Logger log = LoggerFactory.getLogger(TrackingLog.class);

    /**
     * info log format
     * definition implements...
     */
    static String FORMAT_INFO = "";

    /**
     * warn log format
     * definition implements...
     */
    static String FORMAT_WARN = "";

    /**
     * error log format
     * definition implements...
     */
    static String FORMAT_ERROR = "";

    /**
     * debug log format
     * definition implements...
     */
    static String FORMAT_DEBUG = "";

    /**
     * info logging method.<BR/>
     *
     * @param param parameter list
     */
    public static void info(String param) {
        log.info(FORMAT_INFO, param);
    }

    /**
     * debug logging method.<BR/>
     *
     * @param param parameter list
     */
    public static void debug(String param) {
        log.debug(FORMAT_DEBUG, param);
    }

    /**
     * warn logging method.<BR/>
     *
     * @param param parameter list
     */
    public static void warn(String param) {
        log.warn(FORMAT_INFO, param);
    }

    /**
     * error logging method.<BR/>
     *
     * @param param parameter list
     */
    public static void error(String param) {
        log.error(FORMAT_ERROR, param);
    }
}
