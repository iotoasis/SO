package com.pineone.icbms.so.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {

    public static String currentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static String currentTime(long time){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(new Date(time));
    }

}
