package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Siren device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSSirenDeviceDriver extends ASirenDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSSirenDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String sirenControl(IGenericDeviceContext context) {
        log.info("SSSirenDeviceDriver sirenControl");
        return driverManager.deviceExecute(context);
    }

}
