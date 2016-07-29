package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Action to set the alarm time control.<BR/>
 * Created by pahnj on 2016-01-22.
 */
public class AlarmTimeSettingAction extends DefaultAction {

    private final Logger log = LoggerFactory
            .getLogger(AlarmTimeSettingAction.class);

    @Override
    public void execute(IGenericContext context) {
        log.info("AlarmTimeSettingAction execute");

        int second = 1000;
        int min = second * 60;
        long time = System.currentTimeMillis();
        time =  time + 5*min;
        SimpleDateFormat  dayTime = new SimpleDateFormat("HH:mm");
        String timeStr = dayTime.format(new Date(time));
//        timeStr = timeStr.substring(0, timeStr.length() - 1).concat("0");

        log.debug("AlarmTimeSettingAction time data = " + timeStr);
        /**
         * alarmtime setting<BR/>
         */
        context.addValue(IotServiceContext.ACTION_ALARMINFO_WAKETIME,timeStr);

    }
}
