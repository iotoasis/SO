package com.pineone.icbms.so.iot.resources.driver.mapper;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.driver.IGenericDeviceDriver;

/**
 * Device driver mapper abstract class.<BR/>
 * Created by uni4love on 2015. 12. 28..
 */
abstract public class AGenericDeviceDriverMapper<DEVICE_DRIVER extends IGenericDeviceDriver, DEVICE_CONTEXT extends IGenericDeviceContext>
		implements IGenericDeviceDriverMapper<DEVICE_DRIVER, DEVICE_CONTEXT>
{
}
