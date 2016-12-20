package com.pineone.icbms.so.device.store;

import com.pineone.icbms.so.device.store.mongo.DeviceSubscriptionObject;

public interface DeviceSubscriptionStore {
    void create(DeviceSubscriptionObject deviceSubscriptionObject);
    DeviceSubscriptionObject retrieve(String commandId);
    void delete(String commandId);
    void update(DeviceSubscriptionObject deviceSubscriptionObject);
}
