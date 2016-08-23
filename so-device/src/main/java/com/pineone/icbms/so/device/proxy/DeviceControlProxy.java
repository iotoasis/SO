package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.ResultMessage;

public interface DeviceControlProxy {
    ResultMessage deviceControlRequest(String requestUrl, String requestBody);
}
