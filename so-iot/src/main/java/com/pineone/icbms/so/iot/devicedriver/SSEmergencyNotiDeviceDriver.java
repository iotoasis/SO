package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EmergencyNoti device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSEmergencyNotiDeviceDriver extends AEmergencyNotiDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSEmergencyNotiDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String emergencyNotiControl(IGenericDeviceContext context) {
        log.info("SSEmergencyNotiDeviceDriver emergencyNotiControl");
        return driverManager.deviceExecute(context);
    }
}
