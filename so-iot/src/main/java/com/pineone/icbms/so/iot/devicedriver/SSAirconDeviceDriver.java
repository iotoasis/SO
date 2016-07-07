package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aircon device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSAirconDeviceDriver extends AAirconDeviceDriver
{

	private final Logger log = LoggerFactory
			.getLogger(SSAirconDeviceDriver.class);
	DeviceDriverManager driverManager = new DeviceDriverManager();

	@Override
	String airconControl(IGenericDeviceContext context)
	{
		log.info("SSAirconDeviceDriver airconControl");
        return driverManager.deviceExecute(context);
	}
}
