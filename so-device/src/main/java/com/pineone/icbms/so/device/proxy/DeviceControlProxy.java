package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.DeviceControlMessage;
import com.pineone.icbms.so.device.entity.DeviceSubscriptionData;
import com.pineone.icbms.so.device.entity.ResultMessage;

public interface DeviceControlProxy {
    ResultMessage deviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage);
    String deviceSubscriptionRequest(String requestUri, DeviceSubscriptionData deviceSubscriptionData);
}
