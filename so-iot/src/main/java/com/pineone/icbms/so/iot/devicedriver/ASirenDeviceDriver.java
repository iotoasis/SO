package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Siren device driver.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ASirenDeviceDriver extends DefaultDeviceDriver
{
	/**
	 * siren execute
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
			case IGenericOntologyReference.REF_SIREN_CONTROL:
				result = sirenControl(context);
				break;
		}
		return result;
	}

	abstract String sirenControl(IGenericDeviceContext context);

}
