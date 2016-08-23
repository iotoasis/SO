package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.Device;

import java.util.List;

public interface DeviceICollectionProxy {
    List<String> findDomain(String requestUri);
    Device findDeviceByID(String requestUri);
    List<Device> findDeviceByDomain(String requestUri);
    List<String> findDeivceServiceList(String requestUri);
    String findDeviceOperation(String deviceId, String deviceService);
}
