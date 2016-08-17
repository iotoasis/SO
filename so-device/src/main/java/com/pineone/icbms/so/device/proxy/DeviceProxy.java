package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;

import java.util.List;

public interface DeviceProxy {
    String deviceControlRequest(String requestUrl,String requestBody);
    List<String> findDomain(String requestUri);
    Device findDeviceByID(String requestUri);
    List<Device> findDeviceByDomain(String requestUri);
    List<String> findDeivceFunctionality(String requestUri);
}
