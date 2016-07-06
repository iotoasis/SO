package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Humidifier device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSHumidifierDeviceDriver extends AHumidifierDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSHumidifierDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String humidifierControl(IGenericDeviceContext context) {
        log.info("SSHumidifierDeviceDriver humidifierControl");
        System.out.println("SSHumidifierDeviceDriver humidifierControl");
        return driverManager.deviceExecute(context);
    }
}
