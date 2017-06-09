package com.pineone.icbms.so.util.conversion;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Data Conversion Class<BR/>
 * Created by use on 2015-10-12.
 * NOTE: 데이터 변환시 사용
 */
public class DataConversion
{

	private DataConversion() {
		throw new IllegalAccessError("Utility class");
	}

	/**
     * Object Data to String.<BR/>
     * @param o
     * @return
     * NOTE: 오브젝트를 String 형태 데이터로 변경
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
		catch (JsonMappingException |JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return data;
	}

    public static String listDataToString(List<String> list){
        return new Gson().toJson(list);
    }

    public static List<String> stringDataToList(String data){
        Type type = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(data,type);
    }

	public static String base64encoding(String data){
		return new String(Base64.encodeBase64(data.getBytes()));
	}


}
