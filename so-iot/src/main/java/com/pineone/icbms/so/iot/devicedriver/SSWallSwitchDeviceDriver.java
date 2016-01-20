package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WallSwitch device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSWallSwitchDeviceDriver extends AWallSwitchDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSWallSwitchDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String wallSwitchControl(IGenericDeviceContext context) {
        log.info("SSWallSwitchDeviceDriver wallSwitchControl");
        return driverManager.deviceExecute(context);
    }

}
