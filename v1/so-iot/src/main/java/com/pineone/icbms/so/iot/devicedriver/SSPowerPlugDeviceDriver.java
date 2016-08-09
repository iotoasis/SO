package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PowerPlug device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSPowerPlugDeviceDriver extends APowerPlugDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSPowerPlugDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();


    @Override
    String powerPlugControl(IGenericDeviceContext context) {
        log.info("SSPowerPlugDeviceDriver powerPlguControl");
        return driverManager.deviceExecute(context);
    }

}
