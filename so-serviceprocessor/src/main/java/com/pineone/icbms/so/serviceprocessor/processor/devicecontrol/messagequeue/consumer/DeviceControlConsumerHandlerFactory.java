package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.messagequeue.consumer;

/**
 * SpringKafkaDeviceControlConsumerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class DeviceControlConsumerHandlerFactory {
    /**
     * max count
     */
    private int MAX_COUNT = 5;

    /**
     * return SpringKafkaContextModelConsumerHandler instance.<BR/>
     *
     * @return SpringKafkaContextModelConsumerHandler
     */
    public static DeviceControlConsumerHandler getDeviceControlConsumer(int id) {
        return new DeviceControlConsumerHandler(id);
    }
}
