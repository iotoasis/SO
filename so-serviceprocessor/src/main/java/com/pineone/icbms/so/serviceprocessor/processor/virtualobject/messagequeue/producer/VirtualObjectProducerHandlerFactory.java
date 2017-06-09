package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.producer;

/**
 * SpringKafkaVirtualObjectProducerHandler factory.<BR/>
 * Created by uni4love on 2017. 1. 4..
 */
public class VirtualObjectProducerHandlerFactory {
    /**
     * return SpringKafkaVirtualObjectProducerHandler instance.<BR/>
     *
     * @return SpringKafkaVirtualObjectProducerHandler
     */
    public static VirtualObjectProducerHandler getVirtualObjectProducerHandler(int id) {
        return new VirtualObjectProducerHandler(id);
    }
}
