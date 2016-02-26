package com.pineone.icbms.so.iot.devicedriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * SmartSwitch device driver abstract.<BR/>
 * Created by use on 2015-10-29.
 */
public abstract class ASmartSwitchDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(ASmartSwitchDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("ASmartSwitchDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("ASmartSwitchDeviceDriver ontologyReference = " + ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = smartSwitchControl(context);
				break;
		}
		log.debug("ASmartSwitchDeviceDriver result = " + result);
		return result;
	}

	abstract String smartSwitchControl(IGenericDeviceContext context);
}
