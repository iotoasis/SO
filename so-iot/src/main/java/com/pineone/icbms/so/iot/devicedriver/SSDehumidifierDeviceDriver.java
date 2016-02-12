package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by use on 2015-11-04.
 */
public class SSDehumidifierDeviceDriver extends ADeHumidifierDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSDehumidifierDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String deHumidifierControl(IGenericDeviceContext context) {
        log.info("SSDehumidifierDeviceDriver powerControl");
        return driverManager.deviceExecute(context);
    }
}