package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AlarmInfo device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSAlarmInfoDeviceDriver extends AAlarmInfoDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSAlarmInfoDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();


    @Override
    String alarmInfoControl(IGenericDeviceContext context) {
        log.info("SSAlarmInfoDeviceDriver alarmControl ");
        return driverManager.deviceExecute(context);
    }

}
