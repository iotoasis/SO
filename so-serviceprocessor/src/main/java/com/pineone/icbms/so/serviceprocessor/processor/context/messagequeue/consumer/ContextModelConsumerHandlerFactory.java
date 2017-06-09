package com.pineone.icbms.so.serviceprocessor.processor.context.messagequeue.consumer;

/**
 * SpringKafkaContextModelConsumerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class ContextModelConsumerHandlerFactory {
    /**
     * max count
     */
    private int MAX_COUNT = 5;

    /**
     * return SpringKafkaContextModelConsumerHandler instance.<BR/>
     *
     * @return ContextModelConsumer
     */
    public static ContextModelConsumerHandler getContextModelConsumer(int id) {
        return new ContextModelConsumerHandler(id);
    }
}
