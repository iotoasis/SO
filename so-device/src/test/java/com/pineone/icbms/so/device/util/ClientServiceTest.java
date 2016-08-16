package com.pineone.icbms.so.device.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientServiceTest {

    public ClientService clientservice;

    @Before
    public void setUp(){
        clientservice = new ClientService();
    }

    @Test
    public void requestPostServiceTest(){
        //

        // TODO : TestCode 제 수정 예정
        /*String response = clientservice
                .requestPostService("http://httpbin.org/post","{\"name\":\"test\"}");

        System.out.println(response);
        assertEqualsResponse(response,"{  \"args\": {},   \"data\": \"{\\\"name\\\":\\\"test\\\"}\",   \"files\": {},   \"form\": {},   \"headers\": {    \"Content-Length\": \"15\",     \"Content-Type\": \"application/json\",     \"Host\": \"httpbin.org\"  },   \"json\": {    \"name\": \"test\"  },   \"origin\": \"219.248.137.6\",   \"url\": \"http://httpbin.org/post\"}");*/
    }

    @Test
    public void requestGetServiceTest(){
        //
        /**
         * Process
         */
        // TODO : TestCode 제 수정 예정
        /*String response = clientservice
                .requestGetService("http://httpbin.org/get");

        System.out.println(response);

        *//**
         * Expect Result
         *//*
        assertEqualsResponse(response,"{  \"args\": {},   \"headers\": {    \"Host\": \"httpbin.org\"  },   \"origin\": \"219.248.137.6\",   \"url\": \"http://httpbin.org/get\"}");*/
    }

    public void assertEqualsResponse(String response, String resultData)
    {
        assertEquals(response, resultData);

    }



}
