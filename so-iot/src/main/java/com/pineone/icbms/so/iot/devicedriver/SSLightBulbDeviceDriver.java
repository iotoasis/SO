package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by use on 2015-10-29.
 */
public class SSLightBulbDeviceDriver extends ALightBulbDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSLightBulbDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String lightBulbControl(IGenericDeviceContext context) {
        log.info("SSLightBulbDeviceDriver lightBulbControl");
        return driverManager.deviceExecute(context);
    }

}
