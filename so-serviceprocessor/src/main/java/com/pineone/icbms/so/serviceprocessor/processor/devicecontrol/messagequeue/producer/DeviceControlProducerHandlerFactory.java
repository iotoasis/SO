package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.messagequeue.producer;

/**
 * SpringKafkaDeviceControlProducerHandler factory<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class DeviceControlProducerHandlerFactory {
    /**
     * return SpringKafkaDeviceControlProducerHandler instance.<BR/>
     *
     * @return SpringKafkaDeviceControlProducerHandler
     */
    public static DeviceControlProducerHandler getDeviceControlProducerHandler(int id) {
        return new DeviceControlProducerHandler(id);
    }
}
