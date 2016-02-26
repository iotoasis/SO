package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aircon device driver abstract.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class AAirconDeviceDriver extends DefaultDeviceDriver
{

	private final Logger log = LoggerFactory
			.getLogger(AAirconDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("AAirconDeviceDriver execute start ");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("AAirconDeviceDriver ontologyReference = "
				+ ontologyReference.getReference());

		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_AIR_COOLING_CONTROL:
				result = airconControl(context);
				break;
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = airconControl(context);
				break;
		}
		log.debug("AAirconDeviceDriver result = " + result);
		return result;
	}

	abstract String airconControl(IGenericDeviceContext context);
}
