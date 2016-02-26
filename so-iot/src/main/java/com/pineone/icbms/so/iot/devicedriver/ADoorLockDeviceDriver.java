package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * DoorLock device driver abstract.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class ADoorLockDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(ADoorLockDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("ADoorLockDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("ADoorLockDeviceDriver ontologyReference = " + ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = doorlockControl(context);
				break;
			case IGenericOntologyReference.REF_DOOR_CONTROL:
				result = doorlockControl(context);
				break;
		}
		log.debug("ADoorLockDeviceDriver result = " + result);
		return result;
	}

	abstract String doorlockControl(IGenericDeviceContext context);

}
