package com.pineone.icbms.so.devicecontrol.util;

import com.pineone.icbms.so.devicecontrol.model.virtualdevice.driver.IGenericDeviceDriver;

/**
 * Device driver manager interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 9..
 */
public interface IDeviceDriverManager {
    void registerDeviceDriver();

    IGenericDeviceDriver getDriver();
}
