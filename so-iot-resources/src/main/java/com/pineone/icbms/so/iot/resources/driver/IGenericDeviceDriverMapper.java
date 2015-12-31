package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IDeviceContext;

/**
 * Device drvier mapper generic interface.<BR/>
 * Created by uni4love on 2015. 11. 28..
 */
public interface IGenericDeviceDriverMapper<IGenericDeviceDriver extends IDeviceDriver, IGenericDeviceContext extends IDeviceContext>
		extends IDeviceDriverMapper<IGenericDeviceDriver, IGenericDeviceContext>
{
}
