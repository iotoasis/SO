package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Humidifier device driver.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class AHumidifierDeviceDriver extends DefaultDeviceDriver
{
	/**
	 * humidifier execute
	 * 
	 * @param context
	 * @return
	 */
	@Override
	public String execute(IGenericDeviceContext context)
	{
		System.out.println("AHumidifierDeviceDriver  execute");
		String ontologyReference = ((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE)).getReference();

		String result = "";
		System.out.println("AHumidifierDeviceDriver  execute ontologyref111 = " + ontologyReference);
		switch (ontologyReference)
		{
			case IGenericOntologyReference.REF_HUMIDIFY_CONTROL:
				System.out.println("AHumidifierDeviceDriver equals");
				result = humidifierControl(context);
				break;
		}
		return result;
	}

	abstract String humidifierControl(IGenericDeviceContext context);
}
