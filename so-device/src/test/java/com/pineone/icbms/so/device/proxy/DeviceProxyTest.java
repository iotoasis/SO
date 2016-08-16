package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.util.ClientProfile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceProxyTest {

    private DeviceProxy deviceProxy;

    @Before
    public void setUp(){
        deviceProxy =  mock(DeviceProxy.class);
    }


    @Test
    public void findDomainTest(){
        List<String> domainlist = new ArrayList<>();

        List<String> domainTestList = new ArrayList<>();
        domainTestList.add("classroom");

        when(deviceProxy.findDomain(ClientProfile.SDA_DATAREQUEST_URI)).thenReturn(domainTestList);
        domainlist = deviceProxy.findDomain(ClientProfile.SDA_DATAREQUEST_URI);
        Assert.assertEquals(domainlist.get(0),"classroom");

    }

    @Test
    public void findDeviceByIDTest(){
        Device device = new Device();
        Device deviceTest = new Device();
        deviceTest.setDeviceName("JunitTestDevice");
        deviceTest.setDeviceId("deviceT001");

        when(deviceProxy.findDeviceByID(ClientProfile.SDA_DATAREQUEST_URI + "device/deviceT001")).thenReturn(deviceTest);
        device = deviceProxy.findDeviceByID(ClientProfile.SDA_DATAREQUEST_URI + "device/deviceT001");
        Assert.assertEquals(device,deviceTest);
    }

    @Test
    public void findDeviceByDomainTest(){
        List<Device> devices = new ArrayList<>();
        List<Device> deviceTestList = new ArrayList<>();
        Device deviceTemp = new Device();
        deviceTemp.setDeviceName("JunitTestDevice");
        deviceTemp.setDeviceId("deviceT001");
        deviceTestList.add(deviceTemp);

        when(deviceProxy.findDeviceByDomain(ClientProfile.SDA_DATAREQUEST_URI + "device/")).thenReturn(deviceTestList);
        devices = deviceProxy.findDeviceByDomain(ClientProfile.SDA_DATAREQUEST_URI + "device/");
        Assert.assertEquals(devices,deviceTestList);

    }

    @Test
    public void findDeivceFunctionalityTest(){
        List<String> deviceFunctionalityList = new ArrayList<>();
        List<String> deviceFunctionalityListTest = new ArrayList<>();
        deviceFunctionalityListTest.add("TempControl");

        when(deviceProxy.findDeivceFunctionality(ClientProfile.SDA_DATAREQUEST_URI +"devices/functionality/deviceT001")).thenReturn(deviceFunctionalityListTest);
        deviceFunctionalityList = deviceProxy.findDeivceFunctionality(ClientProfile.SDA_DATAREQUEST_URI + "devices/functionality/deviceT001");
        Assert.assertEquals(deviceFunctionalityList,deviceFunctionalityListTest);
    }

    @Test
    public void deviceControlRequestTest(){
        String resultData = null;
        String resultTestData = "success";

        when(deviceProxy.deviceControlRequest(ClientProfile.SI_CONTOL_URI,"testBody")).thenReturn(resultTestData);
        resultData = deviceProxy.deviceControlRequest(ClientProfile.SI_CONTOL_URI,"testBody");
        Assert.assertEquals(resultData,resultTestData);
    }


}
