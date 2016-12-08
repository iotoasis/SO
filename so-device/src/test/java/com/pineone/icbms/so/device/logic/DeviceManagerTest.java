package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.store.mongo.DeviceSubscriptionRepository;
import org.junit.Before;
import org.junit.Test;

public class DeviceManagerTest {

    private DeviceManager deviceManager;

    private DeviceSubscriptionRepository deviceSubscriptionRepository;

    @Before
    public void setUp(){
        deviceManager = new DeviceManagerLogic();
    }

    @Test
    public void deviceRegisterTest(){
    }


    @Test
    public void deviceReleaseTest(){
    }

    @Test
    public void deviceExecuteTest(){
    }

    @Test
    public void deviceControlResultTest(){

    }

    @Test
    public void getOnem2mDeviceUri(){
        //
        String uri ="/herit-in/herit-cse/ONDB_SmartLight08_001/Power/status/CONTENT_INST_427988";
        String oneM2Muri = "";
        int stringlength = 3;
        String[] strings = uri.split("/");
        for(String s : strings){
            System.out.println(s);
        }

        for(int i =1; i<4 ; i++){
            stringlength += strings[i].length();
        }


    }

}
