package com.pineone.icbms.so.iot.util.service;

import com.withwiz.beach.network.http.message.IHttpResponseMessage;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Data Conversion Class<BR/>
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

    /**
     * Object Data to String.<BR/>
     * @param o
     * @return
     */
	public static String objectToString(Object o)
	{
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

}
