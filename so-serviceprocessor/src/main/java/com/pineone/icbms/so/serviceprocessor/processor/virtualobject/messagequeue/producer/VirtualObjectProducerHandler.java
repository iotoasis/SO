package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.producer;

import com.pineone.icbms.so.util.messagequeue.producer.AGenericProducerHandler;

/**
 * VirtualObjectForMQ Producer handler.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
public class VirtualObjectProducerHandler extends AGenericProducerHandler {
    /**
     * topic list
     */
    private static final String TOPIC = "virtualobject";

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = VirtualObjectProducerHandler.class.getSimpleName();

    /**
     * constructor.<BR/>
     */
    public VirtualObjectProducerHandler(int id) {
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
