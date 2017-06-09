package com.pineone.icbms.so.serviceprocessor.processor.context.messagequeue.producer;

import com.pineone.icbms.so.util.messagequeue.producer.AGenericProducerHandler;

/**
 * ContextModelForMQ Producer handler.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
public class ContextModelProducerHandler extends AGenericProducerHandler {
    /**
     * topic list
     */
    private static final String TOPIC = "contextmodel";

    /**
     * kafka producer group id by class name.<BR/>
     */
    private final String GROUP_ID = getClass().getSimpleName();

    /**
     * constructor.<BR/>
     */
    public ContextModelProducerHandler(int id) {
        super(id);
    }

    /**
     * return topic.<BR/>
     *
     * @return topic
     */
    @Override
    public String getTopic() {
        return TOPIC;
    }
}
