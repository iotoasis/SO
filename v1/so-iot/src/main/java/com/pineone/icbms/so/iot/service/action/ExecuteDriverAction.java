package com.pineone.icbms.so.iot.service.action;

import com.pineone.icbms.so.iot.devicedriver.DefaultDeviceDriver;
import com.pineone.icbms.so.iot.resources.context.DefaultDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.person.DefaultStudent;
import com.pineone.icbms.so.iot.resources.vo.pd.DefaultPhysicalDevice;
import com.pineone.icbms.so.resources.action.DefaultAction;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.property.reference.DefaultOntologyReference;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to the device.<BR/>
 * Created by pahnj on 2016-01-12.
 */
public class ExecuteDriverAction extends DefaultAction
{

	public static final String	SI_COMMAND_ALARMINFO_PERIOD					= "1111111";
	public static final String	SI_COMMAND_ALARMINFO_ALARMTYPE				= "setAlarm";
	public static final String	SI_COMMAND_EMERGENCYNOTI_KIND_OVERCURRENT	= "0";
	public static final String	SO_COMMAND_ALARMINFO_ALARMID				= "1";

	private final Logger log = LoggerFactory
			.getLogger(ExecuteDriverAction.class);

	/**
	 * driver execute action<BR/>
	 * 
	 * @param context
	 */
	@Override
	public void execute(IGenericContext context)
	{
		log.info("device action execute insert");
		ArrayList<DefaultDeviceDriver> deviceDrivers = new ArrayList<>();
		ArrayList<DefaultPhysicalDevice> defaultPhysicalDevices = new ArrayList<>();

		log.info("ServiceContext data get");
		DefaultDeviceContext defaultDeviceContext = new DefaultDeviceContext();
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_OPERATION_VALUE,
				context.getValue(IotServiceContext.ACTION_OPERATION_VALUE));
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_ONTOLOGY_REFERENCE,
				context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE));

		log.debug("OntologyRef data = " + ((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE)).getReference());

		if (IGenericOntologyReference.REF_ALARMINFO_CONTROL.equals(((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE)).getReference()))
		{
			log.info("setting AlarmInfo data");
			setAlarmInfoData(context, defaultDeviceContext);
		}

		else if (IGenericOntologyReference.REF_EMERGENCYNOTI_CONTROL.equals(((DefaultOntologyReference)context.getValue(IotServiceContext.ACTION_ONTOLOGY_REFERENCE)).getReference()))
		{
			log.info("setting EmergencyNoti data");
			setEmergencyNotiData(context, defaultDeviceContext);
		}

		for (DefaultDeviceDriver driver : (ArrayList<DefaultDeviceDriver>) context
				.getValue(IotServiceContext.ACTION_DEVICE_DRIVER))
		{
			deviceDrivers.add(driver);
		}

		for (DefaultPhysicalDevice device : (List<DefaultPhysicalDevice>) context
				.getValue(IotServiceContext.ACTION_DEVICE_URI))
		{
			defaultPhysicalDevices.add(device);
		}

		log.debug("[[deviceDrivers]] size = " + deviceDrivers.size());

		for (int i = 0; i < deviceDrivers.size(); i++)
		{
			log.info("device execute action start" + i);
			log.debug("device execute action start "
					+ defaultPhysicalDevices.get(i).getUri());
			defaultDeviceContext.removeValue(
					IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE);
			defaultDeviceContext.addValue(
					IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE,
					defaultPhysicalDevices.get(i).getUri());
			deviceDrivers.get(i).execute(defaultDeviceContext);
		}

	}

	/**
	 * add alarminfo data<BR/>
	 */
	private void setAlarmInfoData(IGenericContext context,
			DefaultDeviceContext defaultDeviceContext)
	{
		log.info("set AlarmInfo Data");

		defaultDeviceContext.addValue(IotServiceContext.ACTION_PHYSICAL_USERID,
				((List<DefaultStudent>) context
						.getValue(IotServiceContext.ACTION_STUDENT_DETAIL)).get(0)
								.getId());
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_ALARMINFO_ALARMID,
				SO_COMMAND_ALARMINFO_ALARMID);
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_ALARMINFO_WAKETIME,
				context.getValue(IotServiceContext.ACTION_ALARMINFO_WAKETIME));
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_ALARMINFO_PERIOD,
				SI_COMMAND_ALARMINFO_PERIOD);
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_ALARMINFO_TYPE,
				SI_COMMAND_ALARMINFO_ALARMTYPE);
	}

	/**
	 * add emergencynoti data<BR/>
	 */
	private void setEmergencyNotiData(IGenericContext context,
			DefaultDeviceContext defaultDeviceContext)
	{
		log.info("set EmergencyNoti Data");
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_ZONE,
				context.getValue(IotServiceContext.ACTION_TARGET_LOCATION));
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_KIND,
				SI_COMMAND_EMERGENCYNOTI_KIND_OVERCURRENT);
		defaultDeviceContext
				.addValue(IotServiceContext.ACTION_PHYSICAL_USERID,
						((List<DefaultStudent>) context.getValue(
								IotServiceContext.ACTION_STUDENT_DETAIL))
										.get(0).getId());
		defaultDeviceContext.addValue(
				IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_CAMURL,
				((List<DefaultPhysicalDevice>) context.getValue(
						IotServiceContext.ACTION_EMERGENCYNOTI_CAMURL)).get(0)
								.getUri());
	}
}
