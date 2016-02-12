package com.pineone.icbms.so.iot.devicedriver;


import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DoorLock device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSDoorLockDeviceDriver extends ADoorLockDeviceDriver {

    private final Logger log = LoggerFactory.getLogger(SSDoorLockDeviceDriver.class);

    DeviceDriverManager driverManager = new DeviceDriverManager();

    @Override
    String doorlockControl(IGenericDeviceContext context) {
        log.info("SSDoorLockDeviceDriver doorlockControl");
        return driverManager.deviceExecute(context);
    }
}
