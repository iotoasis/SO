package com.pineone.icbms.so.device.proxy;

import com.pineone.icbms.so.device.entity.DeviceControlMessage;
import com.pineone.icbms.so.device.entity.ResultMessage;

/**
 * Device 제어 요청
 * Device 상태 구독 요청
 * Device 상태 구독 해지 요청
 */
public interface DeviceControlProxy {
    /**
     * Device 제어 요청
     * @param requestUrl
     * @param deviceControlMessage
     * @return
     */
    ResultMessage deviceControlRequest(String requestUrl, DeviceControlMessage deviceControlMessage);

    /**
     * Device 구독 요청
     * @param deviceUri
     * @return
     */
    String deviceSubscriptionRequest(String deviceUri);

    /**
     * Device 구독 해지 요청
     * @param deviceUri
     * @return
     */
    String deviceSubscriptionReleaseRequest(String deviceUri);

}
