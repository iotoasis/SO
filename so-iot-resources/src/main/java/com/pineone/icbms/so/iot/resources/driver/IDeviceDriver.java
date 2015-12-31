package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.resources.driver.IDriver;

/**
 * Device driver interface.<BR/>
 * Created by uni4love on 2015. 12. 14..
 */
public interface IDeviceDriver<RESULT, IDeviceContext> extends IDriver
{
    /**
     * execute the driver.<BR/>
     *
     * @param context
     *            device context
     */
    RESULT execute(IDeviceContext context);
}
