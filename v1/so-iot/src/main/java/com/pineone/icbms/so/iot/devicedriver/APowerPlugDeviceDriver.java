package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * PowerPlug device driver abstract.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class APowerPlugDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(APowerPlugDeviceDriver.class);


	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("APowerPlugDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("APowerPlugDeviceDriver ontologyReference = " + ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = powerPlugControl(context);
				break;
			case IGenericOntologyReference.REF_DEHUMIDIFY_CONTROL:
				result = powerPlugControl(context);
				break;
		}
		log.debug("APowerPlugDeviceDriver result = " + result);
		return result;
	}

	abstract String powerPlugControl(IGenericDeviceContext context);

}
