package com.pineone.icbms.so.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by melvin on 2017. 3. 28..
 */

//NOTE : Date format
public class DateFormat {

    public static String dateFormat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String result = format.format(date);
        return result;
    }
}
