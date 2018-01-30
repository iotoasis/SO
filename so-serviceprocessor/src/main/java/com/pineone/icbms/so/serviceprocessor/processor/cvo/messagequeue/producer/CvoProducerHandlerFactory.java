package com.pineone.icbms.so.serviceprocessor.processor.cvo.messagequeue.producer;

/**
 * SpringKafkaCvoProducerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class CvoProducerHandlerFactory {
    /**
     * return SpringKafkaCvoProducerHandler instance.<BR/>
     *
     * @return SpringKafkaCvoProducerHandler
     */
    public static CvoProducerHandler getCvoProducerHandler(int id) {
        return new CvoProducerHandler(id);
    }
}
