package com.pineone.icbms.so.iot.devicedriver;

import com.pineone.icbms.so.iot.provider.DriverResultProvider;
import com.pineone.icbms.so.iot.resources.context.IGenericDeviceContext;
import com.pineone.icbms.so.iot.resources.context.IotServiceContext;
import com.pineone.icbms.so.iot.resources.message.AlarmInfoMessage;
import com.pineone.icbms.so.iot.resources.message.DeviceControlMessage;
import com.pineone.icbms.so.iot.resources.message.EmergencyNotiMessage;
import com.pineone.icbms.so.iot.resources.model.repo.driver.result.DriverResultModel;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import com.pineone.icbms.so.resources.property.operation.DefaultOperationValue;
import com.pineone.icbms.so.util.restful.ClientService;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by use on 2015-11-12.
 */
public class DeviceDriverManager
{

	public static final int	KEY_SUCCESS_CODE			= 200;
	public static final int	KEY_SI_RESULT_SUCCESS_CODE	= 2000;

	public static final String	SO_CONTROL_NOTIFICATON_URI	= "http://166.104.112.45:10080/so/resources/vdcm/";
	public static final String	SO_CONTROL_TYPE				= "text/plain:0";
	public static final String	SO_CONTROL_ACTION			= "action";
	public static final String	SI_CONNECT_URI				= "http://166.104.112.34:8081/si/command";
	public static final String	SI_COMMAND_ID				= "cmd_";
	public static final String  SI_DEVICE_URL				= "http://www.pineone.com";

	public String	deviceId;
	public String	deviceCommand;
	public String	operationValue;
	public String	requestData;

	ClientService clientService = new ClientService();
	// OldClientService oldClientService = new OldClientService();

	DriverResultProvider	driverResultProvider	= new DriverResultProvider();
	DriverResultModel		driverResultModel		= new DriverResultModel();
	AlarmInfoMessage		alarmInfoMessage		= new AlarmInfoMessage();
	EmergencyNotiMessage	emergencyNotiMessage	= new EmergencyNotiMessage();
	private final Logger	log						= LoggerFactory
			.getLogger(DeviceDriverManager.class);

	public DeviceDriverManager()
	{
	}

	public String deviceExecute(IGenericDeviceContext context)
	{
        log.info("device execute");

		/**
		 * Context get the deviceId, devicecommand, operationValue<BR/>
		 */
		deviceId = (String) context.getValue(IotServiceContext.ACTION_PHYSICAL_DEVICE_URI_ONE);
		if(deviceId.startsWith(SI_DEVICE_URL))
		{
			deviceId = deviceId.substring(SI_DEVICE_URL.length());
		}
		deviceCommand = SI_COMMAND_ID + System.nanoTime();
		if(context.getValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_ALARMID) != null)
		{
			alarmInfoMessageCreate(context);
			operationValue = DataConversion.objectToString(alarmInfoMessage);
		} else if(context.getValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_ZONE) != null)
		{
			emergencyNotiMessageCreate(context);
			operationValue = DataConversion.objectToString(emergencyNotiMessage);
		} else {
			operationValue = ((DefaultOperationValue)context.getValue(IotServiceContext.ACTION_PHYSICAL_OPERATION_VALUE)).getOperationValue();
		}


		/**
		 * DeviceControlMessage setting<BR/>
		 */
		DeviceControlMessage controlMessage = new DeviceControlMessage(deviceId,
				SO_CONTROL_NOTIFICATON_URI + deviceCommand, deviceCommand, SO_CONTROL_ACTION,
				SO_CONTROL_TYPE, operationValue);
		requestData = DataConversion.objectToString(controlMessage);

		/**
		 * SI stored in the transfer data.<BR/>
		 */
		driverResultModel.setId(deviceCommand);
		driverResultModel.setSendMessage(requestData);
		driverResultProvider.setData(driverResultModel);

		/**
		 * Control requests to the SI.<BR/>
		 */
		IHttpResponseMessage responseMessage = clientService
				.requestPostService(SI_CONNECT_URI, requestData);


		/**
		 * Confirmation control results. And results delivered.<BR/>
		 */
		if (responseMessage.getStatusCode() == KEY_SI_RESULT_SUCCESS_CODE || responseMessage.getStatusCode() == KEY_SUCCESS_CODE)
		{
			log.info("device execute result success");
			driverResultModel.setResult1(DataConversion.RESPONSE_SUCCESS);
			driverResultProvider.updateDataByID(deviceCommand,driverResultModel);
			return Integer.toString(KEY_SUCCESS_CODE);
		}
		else
		{
			log.info("device execute result fail");
			driverResultModel.setResult1(Integer.toString(responseMessage.getStatusCode()));
			driverResultProvider.updateDataByID(deviceCommand,driverResultModel);
			return Integer.toString(responseMessage.getStatusCode());
		}
	}

	/**
	 * create EmergencyNoti Message<BR/>
	 */
	private void emergencyNotiMessageCreate(IGenericDeviceContext context) {
		log.info("EmergencyNoti Message Create");
		emergencyNotiMessage.setUserId((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_USERID));
		emergencyNotiMessage.setZone((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_ZONE));
		emergencyNotiMessage.setKind((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_KIND));
		emergencyNotiMessage.setCamUrl((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_EMERGENCYNOTI_CAMURL));
	}

	/**
	 * create alarmInfo Message<BR/>
	 */
	private void alarmInfoMessageCreate(IGenericDeviceContext context) {
		log.info("AlarmInfo Message Create");
		alarmInfoMessage.setUserId((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_USERID));
		alarmInfoMessage.setAlarmId((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_ALARMID));
		alarmInfoMessage.setPeriod((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_PERIOD));
		alarmInfoMessage.setWakeTime((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_WAKETIME));
		alarmInfoMessage.setActionType((String)context.getValue(IotServiceContext.ACTION_PHYSICAL_ALARMINFO_TYPE));
	}

}
