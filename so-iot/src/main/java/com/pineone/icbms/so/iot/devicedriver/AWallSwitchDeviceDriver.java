package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * WallSwitch device driver.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class AWallSwitchDeviceDriver extends DefaultDeviceDriver
{

	/**
	 * WallSwitch execute
	 * @param context
	 * @return
	 */
	@Override
	public String execute(IGenericDeviceContext context)
	{
		IGenericOntologyReference ontologyReference = context
				.getOntologyReference();
		String result = "";

		switch (ontologyReference.getId())
		{
			case IGenericOntologyReference.REF_WALLSWITCH_CONTROL:
				result = wallSwitchControl(context);
				break;
		}
		return result;
	}

	abstract String wallSwitchControl(IGenericDeviceContext context);

}
