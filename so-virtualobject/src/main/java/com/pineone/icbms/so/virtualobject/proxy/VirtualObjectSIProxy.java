package com.pineone.icbms.so.virtualobject.proxy;

import com.pineone.icbms.so.device.pr.DevicePresentation;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualObjectSIProxy implements VirtualObjectControlProxy {

    public static final Logger logger = LoggerFactory.getLogger(VirtualObjectSIProxy.class);

    @Autowired
    DevicePresentation devicePresentation;

    @Override
    public String executeDevice(String deviceId, String deviceCommand) {
        logger.info(LogPrint.outputInfoLogPrint());
        System.out.println("\n**********  VirtualObject Proxy RequestDeviceControl  **********");
        System.out.println("Request DeviceID = " + deviceId);
        System.out.println("Request DeviceCommand = " + deviceCommand);
        return devicePresentation.deviceControl(devicePresentation.settingDeviceRequestData(deviceId, deviceCommand));
    }
}
