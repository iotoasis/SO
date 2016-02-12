package com.pineone.icbms.so.util;


import com.pineone.icbms.so.iot.resources.message.ResponseMessage;
import com.pineone.icbms.so.iot.util.service.DataConversion;
import com.pineone.icbms.so.server.config.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.Assert.assertEquals;

/**
 * Created by use on 2015-11-02.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppContext.class)
@WebAppConfiguration
public class DataConversionTest {


    @Test
    public void DataServiceDataParsingTest(){
        /**
         * Prepare Resources
         */
        String sampleData = "{\"code\":\"123\",\"message\":\"TEST-DATASERVICE\",\"content\":\"TESTCONTENT\"}";
        ResponseMessage message = new ResponseMessage("123","TEST-DATASERVICE","TESTCONTENT");

        /**
         * Process : DataService.dataParsing(Object)
         * Expect Result : assertEqualsData
         */

        assertEqualsData(sampleData, DataConversion.objectToString(message));

    }

    public void assertEqualsData(String message1, String message2){
        assertEquals(message1,message2);
    }

    public void assertEqualsData(ResponseMessage message1, ResponseMessage message2){
        assertEquals(message1.getCode(),message2.getCode());
        assertEquals(message1.getMessage(),message2.getMessage());
        assertEquals(message1.getContent(),message2.getContent());
    }



    @Test
    public void dataServiceResponseDataParsingToStringTest(){
        System.out.println();
    }


}
