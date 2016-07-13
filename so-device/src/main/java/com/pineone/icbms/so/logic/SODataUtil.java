package com.pineone.icbms.so.logic;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by pahnj on 2016-07-12.
 */
public class SODataUtil {

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



}
