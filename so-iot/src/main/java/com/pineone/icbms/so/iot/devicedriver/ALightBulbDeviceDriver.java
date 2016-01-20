package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * LightBulb device driver.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ALightBulbDeviceDriver extends DefaultDeviceDriver {

	/**
	 * luminosity execute
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
			case IGenericOntologyReference.REF_LUMINOSITY_CONTROL:
				result = lightBulbControl(context);
				break;
		}
		return result;
	}

	abstract String lightBulbControl(IGenericDeviceContext context);

}
