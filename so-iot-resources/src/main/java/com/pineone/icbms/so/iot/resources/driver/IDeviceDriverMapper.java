package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IDeviceContext;

/**
 * Device driver mapper.<BR/>
 * Created by uni4love on 2015. 10. 28..
 */
public interface IDeviceDriverMapper<DEVICE_DRIVER extends IDeviceDriver, DEVICE_CONTEXT extends IDeviceContext>
{
	DEVICE_DRIVER getDeviceDriver(DEVICE_CONTEXT device);
}
