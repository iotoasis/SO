package com.pineone.icbms.so.device.logic;

import com.pineone.icbms.so.device.entity.Device;
import com.pineone.icbms.so.device.entity.ResultMessage;
import com.pineone.icbms.so.device.entity.deviceReleaseMessage;

import java.util.List;

public interface DeviceManager {
    void deviceRegister(deviceReleaseMessage deviceReleaseMessage);
    void deviceRelease(String deviceId);
    String deviceExecute(String deviceId,String deviceCommand);
    String deviceControlResult(ResultMessage resultMessage);
    Device deviceSearchById(String deviceId);
    List<Device> deviceSearchByLocation(String location);
    List<String> requestDeviceServiceList(String location);
    String searchOperation(String deviceId, String deviceService);
    List<Device> searchDeviceList();
    void deviceUpdate(Device device);
    String deviceSubscription(String uri);
}
