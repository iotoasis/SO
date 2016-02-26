package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Dehumidifier device driver abstract.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ADeHumidifierDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(ADeHumidifierDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("ADeHumidifierDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("ADeHumidifierDeviceDriver ontologyReference = "
				+ ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = deHumidifierControl(context);
				break;
			case IGenericOntologyReference.REF_DEHUMIDIFY_CONTROL:
				result = deHumidifierControl(context);
				break;
		}
		log.debug("ADeHumidifierDeviceDriver result = " + result);
		return result;
	}

	abstract String deHumidifierControl(IGenericDeviceContext context);

}
