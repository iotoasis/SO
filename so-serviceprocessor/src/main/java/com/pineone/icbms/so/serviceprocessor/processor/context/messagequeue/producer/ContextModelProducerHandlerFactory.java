package com.pineone.icbms.so.serviceprocessor.processor.context.messagequeue.producer;

/**
 * SpringKafkaContextModelProducerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class ContextModelProducerHandlerFactory {
    /**
     * return SpringKafkaContextModelProducerHandler instance.<BR/>
     *
     * @return SpringKafkaContextModelProducerHandler
     */
    public static ContextModelProducerHandler getContextModelProducerHandler(int id) {
        return new ContextModelProducerHandler(id);
    }
}
