package com.pineone.icbms.so.iot.resources.driver.mapper;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.driver.IGenericDeviceDriver;

/**
 * Device drvier mapper generic interface.<BR/>
 * Created by uni4love on 2015. 11. 28..
 */
public interface IGenericDeviceDriverMapper<DEVICE_DRIVER extends IGenericDeviceDriver, DEVICE_CONTEXT extends IGenericDeviceContext>
		extends IDeviceDriverMapper<DEVICE_DRIVER, DEVICE_CONTEXT>
{
}
