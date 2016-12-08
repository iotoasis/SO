package com.pineone.icbms.so.device.store.mongo;

import com.pineone.icbms.so.device.store.DeviceSubscriptionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * MongoDB와 DeviceSubscrption 연결
 */
@Repository
public class DeviceSubscriptionMongoStore implements DeviceSubscriptionStore {

    public static final Logger logger = LoggerFactory.getLogger(DeviceSubscriptionMongoStore.class);

    @Autowired
    DeviceSubscriptionRepository deviceSubscriptionRepository;

    @Override
    public void create(DeviceSubscriptionObject deviceSubscriptionObject) {
        logger.debug("Device  = " + deviceSubscriptionObject.toString());
        deviceSubscriptionRepository.save(deviceSubscriptionObject);
    }

    @Override
    public DeviceSubscriptionObject retrieve(String commandId) {
        logger.debug("Command ID  = " + commandId);
        return deviceSubscriptionRepository.findBy_commandId(commandId);
    }

    @Override
    public void delete(String commandId) {
        logger.debug("Command ID  = " + commandId);
        deviceSubscriptionRepository.delete(commandId);
    }
}
