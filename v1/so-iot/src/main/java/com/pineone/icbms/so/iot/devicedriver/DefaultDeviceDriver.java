package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.driver.AGenericDeviceDriver;

/**
 * Default Device Driver Class.<BR/>
 * Created by pahnj on 2016-01-13.
 */
public class DefaultDeviceDriver extends AGenericDeviceDriver<String, IGenericDeviceContext> {
    public DefaultDeviceDriver() {
    }

    @Override
    public String execute(IGenericDeviceContext context) {
        return null;
    }
}
