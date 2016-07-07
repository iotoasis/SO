package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;

/**
 * Heater device driver class.<BR/>
 * Created by use on 2015-10-29.
 */
public class SSHeaterDeviceDriver extends AHeaterDeviceDriver
{

	private final Logger log = LoggerFactory
			.getLogger(SSHeaterDeviceDriver.class);
	DeviceDriverManager driverManager = new DeviceDriverManager();

	@Override
	String heaterControl(IGenericDeviceContext context) {
		log.info("SSHeaterDeviceDriver heaterControl");
		return driverManager.deviceExecute(context);
	}
}
