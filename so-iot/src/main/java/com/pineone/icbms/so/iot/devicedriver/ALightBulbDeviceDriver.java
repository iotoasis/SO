package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * LightBulb device driver abstract.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ALightBulbDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(ALightBulbDeviceDriver.class);


	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("ALightBulbDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("ALightBulbDeviceDriver ontologyReference = " + ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = lightBulbControl(context);
				break;
			case IGenericOntologyReference.REF_LUMINOSITY_CONTROL:
				result = lightBulbControl(context);
				break;
		}
		log.debug("ALightBulbDeviceDriver result = " + result);
		return result;
	}

	abstract String lightBulbControl(IGenericDeviceContext context);

}
