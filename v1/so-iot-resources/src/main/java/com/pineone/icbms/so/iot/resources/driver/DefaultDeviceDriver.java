package com.pineone.icbms.so.iot.resources.driver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;

/**
 * Device driver default class.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public class DefaultDeviceDriver<RESULT>
		extends AGenericDeviceDriver<RESULT, IGenericDeviceContext>
{
	@Override
	public RESULT execute(IGenericDeviceContext context)
	{
		return null;
	}
}
