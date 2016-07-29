package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SmartSwitch device driver class.<BR/>
 * Created by use on 2015-10-21.
 */
public class SSSmartSwitchDeviceDriver extends ASmartSwitchDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSSmartSwitchDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String smartSwitchControl(IGenericDeviceContext context) {
        log.info("SSSmartSwitchDeviceDriver powerControl");
        return driverManager.deviceExecute(context);
    }
}
