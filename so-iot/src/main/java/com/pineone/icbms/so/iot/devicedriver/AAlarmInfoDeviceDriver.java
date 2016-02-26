package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AlarmInfo device driver abstract.<BR/>
 * Created by use on 2015-12-29.
 */
public abstract class AAlarmInfoDeviceDriver extends DefaultDeviceDriver
{
	private final Logger log = LoggerFactory
			.getLogger(AAlarmInfoDeviceDriver.class);

	@Override
	public String execute(IGenericDeviceContext context)
	{
		log.info("AAlarmInfoDeviceDriver execute start");
		DefaultOntologyReference ontologyReference = (DefaultOntologyReference) context
				.getValue(IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE);
		String result = "";
		log.debug("AAlarmInfoDeviceDriver ontologyReference = "
				+ ontologyReference.getReference());
		switch (ontologyReference.getReference())
		{
			case IGenericOntologyReference.REF_POWER_CONTROL:
				result = alarmInfoControl(context);
				break;
			case IGenericOntologyReference.REF_ALARMINFO_CONTROL:
				result = alarmInfoControl(context);
				break;
		}
		log.debug("AAlarmInfoDeviceDriver result = " + result);
		return result;
	}

	abstract String alarmInfoControl(IGenericDeviceContext context);

}
