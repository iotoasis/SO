package com.pineone.icbms.so.serviceprocessor.processor.cvo.messagequeue.consumer;

/**
 * SpringKafkaCvoConsumerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class CvoConsumerHandlerFactory {
    /**
     * max count
     */
    private int MAX_COUNT = 5;

    /**
     * return SpringKafkaCvoConsumerHandler instance.<BR/>
     *
     * @return SpringKafkaCvoConsumerHandler
     */
    public static CvoConsumerHandler getCvoConsumerHandler(int id) {
        return new CvoConsumerHandler(id);
    }
}
