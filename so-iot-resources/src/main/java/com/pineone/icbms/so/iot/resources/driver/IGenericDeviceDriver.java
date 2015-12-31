package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IDeviceContext;

/**
 * generic device driver interface.<BR/>
 * Created by uni4love on 2015. 12. 18..
 */
public interface IGenericDeviceDriver<RESULT, IGenericDeviceContext extends IDeviceContext>
		extends
		IDeviceDriver<RESULT, IGenericDeviceContext>
{
	/**
	 * execute the driver.<BR/>
	 * 
	 * @param context
	 *            device context
	 */
	RESULT execute(IGenericDeviceContext context);
}
