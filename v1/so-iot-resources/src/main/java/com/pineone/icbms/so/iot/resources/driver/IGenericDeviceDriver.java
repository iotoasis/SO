package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;

/**
 * generic device driver interface.<BR/>
 * Created by uni4love on 2015. 12. 18..
 */
public interface IGenericDeviceDriver<RESULT, DEVICE_CONTEXT extends IGenericDeviceContext>
		extends IDeviceDriver<RESULT, DEVICE_CONTEXT>
{
	/**
	 * execute the driver.<BR/>
	 * 
	 * @param context
	 *            device context
	 */
	RESULT execute(DEVICE_CONTEXT context);
}
