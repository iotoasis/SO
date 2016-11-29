package com.pineone.icbms.so.util.logprint;

/**
 * Created by melvin on 2016. 9. 12..
 */
public class LogPrint {

    public static String inputInfoLogPrint(){
        return Thread.currentThread().getStackTrace()[2].getMethodName() + " Method : requested ";
    }

    public static String outputInfoLogPrint(){
        return Thread.currentThread().getStackTrace()[2].getMethodName() + " Method : response ";
    }

    public static String LogMethodNamePrint(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
