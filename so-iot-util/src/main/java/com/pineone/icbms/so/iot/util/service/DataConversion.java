package com.pineone.icbms.so.iot.util.service;

import com.pineone.icbms.so.iot.resources.message.ResponseMessage;
import com.pineone.icbms.so.iot.resources.message.ResultMessage;
import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.iot.resources.message.DeviceControlMessage;
import com.pineone.icbms.so.iot.resources.message.VirtualDeviceControlMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by use on 2015-10-12.
 */
public class DataConversion
{

	private static final Logger log = LoggerFactory
			.getLogger(DataConversion.class);

	public static final String	SO_CONTROL_NOTIFICATON_URI	= "http://219.248.137.7:10080/so/resources/vdcm/";
	public static final String	SO_CONTROL_TYPE				= "text/plain:0";
	public static final String	CONTROL_ACTION				= "action";

    public static final String	RESPONSE_SUCCESS			= "success";
    public static final String	RESPONSE_FAILURE			= "failure";
    public static final String	RESPONSE_SUCCESS_CODE		= "200";
    public static final String	RESPONSE_FIALURE_CODE		= "400";
    public static final String	RESPONSE_SUCCESS_ONEM2MCODE	= "2000";

	public static DeviceControlMessage virtualDataConversion(
			VirtualDeviceControlMessage message)
	{

		log.info("virtualDataConversion start");
		DeviceControlMessage deviceControlMessage = new DeviceControlMessage();

		deviceControlMessage.set_uri(message.getDeviceid());
		deviceControlMessage.set_notificationUri(
				SO_CONTROL_NOTIFICATON_URI + message.getId());
		deviceControlMessage.set_commandId(message.getId());
		deviceControlMessage.set_command(CONTROL_ACTION);
		deviceControlMessage.setCnf(SO_CONTROL_TYPE);
		deviceControlMessage.setCon(message.getValue());

		return deviceControlMessage;

	}

	public static String objectToString(Object o)
	{
		log.info("dataparsing object to String");
		ObjectMapper mapper = new ObjectMapper();
		String data = null;
		if(o == null){
			return data;
		}
		try
		{
			data = mapper.writeValueAsString(o);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return data;
	}

    /**
     * response Data to String.<BR/>
     *
     * @param message
     * @return String
     */
    static public String responseDataToString(IHttpResponseMessage message)
    {
        String result = null;

        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(
                    message.getBodyInputStream()), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            result = sb.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    static public ResponseMessage responseMessageByResultMessage(ResultMessage message)
    {
        log.info("responseMessage by resultMessage convert start");

        ResponseMessage responseMessage = new ResponseMessage();

        if (RESPONSE_SUCCESS_ONEM2MCODE.equals(message.get_resultCode())
                || RESPONSE_SUCCESS.equals(message.get_result())
                || RESPONSE_SUCCESS_CODE.equals(message.get_resultCode()))
        {
            responseMessage.setCode(RESPONSE_SUCCESS_CODE);
            responseMessage.setMessage(RESPONSE_SUCCESS);
            responseMessage.setContent(message.get_result());
        }
        else
        {
            responseMessage.setCode(message.get_resultCode());
            responseMessage.setMessage(RESPONSE_FAILURE);
            responseMessage.setContent(message.get_result());
        }

        return responseMessage;
    }

}
