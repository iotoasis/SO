package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.consumer;

/**
 * Created by uni4love on 2017. 1. 4..
 */
public class VirtualObjectConsumerHandlerFactory {
    /**
     * max count
     */
    private int MAX_COUNT = 5;

    /**
     * return SpringKafkaVirtualObjectConsumerHandler instance.<BR/>
     *
     * @return SpringKafkaVirtualObjectConsumerHandler
     */
    public static VirtualObjectConsumerHandler getVirtualObjectConsumerHandler(int id) {
        return new VirtualObjectConsumerHandler(id);
    }
}
