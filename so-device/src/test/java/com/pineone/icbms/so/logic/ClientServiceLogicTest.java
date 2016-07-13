package com.pineone.icbms.so.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pahnj on 2016-07-12.
 */
public class ClientServiceLogicTest {

    public ClientServiceLogic clientserviceLogic;

    @Before
    public void setUp(){
        clientserviceLogic = new ClientServiceLogic();
    }

    @Test
    public void requestPostServiceTest(){
        //
        String response = clientserviceLogic
                .requestPostService("http://httpbin.org/post","{\"name\":\"test\"}");

        System.out.println("aaa");
        System.out.println(response);
        System.out.println("aaa");
        assertEqualsResponse(response,"{  \"args\": {},   \"data\": \"{\\\"name\\\":\\\"test\\\"}\",   \"files\": {},   \"form\": {},   \"headers\": {    \"Content-Length\": \"15\",     \"Content-Type\": \"application/json\",     \"Host\": \"httpbin.org\"  },   \"json\": {    \"name\": \"test\"  },   \"origin\": \"219.248.137.6\",   \"url\": \"http://httpbin.org/post\"}");
    }

    @Test
    public void requestGetServiceTest(){
        //
        /**
         * Process
         */
        String response = clientserviceLogic
                .requestGetService("http://httpbin.org/get");

        System.out.println(response);

        /**
         * Expect Result
         */
        assertEqualsResponse(response,"{  \"args\": {},   \"headers\": {    \"Host\": \"httpbin.org\"  },   \"origin\": \"219.248.137.6\",   \"url\": \"http://httpbin.org/get\"}");
    }

    public void assertEqualsResponse(String response, String resultData)
    {
        assertEquals(response, resultData);

    }

}
