package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Siren device driver abstract.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ASirenDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(ASirenDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("ASirenDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("ASirenDeviceDriver ontologyReference = " + ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = sirenControl(context);
				break;
			case IGenericOntologyReference.REF_SIREN_CONTROL:
				result = sirenControl(context);
				break;
		}
		log.debug("ASirenDeviceDriver result = " + result);
		return result;
	}

	abstract String sirenControl(IGenericDeviceContext context);

}
