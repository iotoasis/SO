package com.pineone.icbms.so.virtualobject.proxy;

import com.pineone.icbms.so.device.pr.DevicePresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualObjectSIProxy implements VirtualObjectControlProxy {

    @Autowired
    DevicePresentation devicePresentation;

    @Override
    public String executeDevice(String deviceId, String deviceCommand) {
        System.out.println("\n**********  VirtualObject Proxy RequestDeviceControl  **********");
        System.out.println("Request DeviceID = " + deviceId);
        System.out.println("Request DeviceCommand = " + deviceCommand);
        return devicePresentation.deviceControl(devicePresentation.settingDeviceRequestData(deviceId, deviceCommand));
    }
}
