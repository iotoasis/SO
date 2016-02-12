package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * EmergencyNoti  device driver.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class AEmergencyNotiDeviceDriver extends DefaultDeviceDriver
{
	/**
	 * doorLock execute
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
			case IGenericOntologyReference.REF_EMERGENCYNOTI_CONTROL:
				result = emergencyNotiControl(context);
				break;
		}
		return result;
	}

	abstract String emergencyNotiControl(IGenericDeviceContext context);
}
