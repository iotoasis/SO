package com.pineone.icbms.so.iot.resources.driver.mapper;

/**
 * Device driver mapper.<BR/>
 * Created by uni4love on 2015. 10. 28..
 */
public interface IDeviceDriverMapper<DEVICE_DRIVER, DEVICE_CONTEXT>
{
	DEVICE_DRIVER getDeviceDriver(DEVICE_CONTEXT device);
}
