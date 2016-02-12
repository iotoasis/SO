package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * DoorLock device driver.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class ADoorLockDeviceDriver	extends DefaultDeviceDriver
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
			case IGenericOntologyReference.REF_DOOR_CONTROL:
				result = doorlockControl(context);
				break;
		}
		return result;
	}

	abstract String doorlockControl(IGenericDeviceContext context);

}
