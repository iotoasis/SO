package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.messagequeue.producer;

import com.pineone.icbms.so.util.messagequeue.producer.AGenericProducerHandler;

/**
 * OrchestrationServiceForMQ Producer handler.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
public class OrchestrationServiceProducerHandler extends AGenericProducerHandler {
    /**
     * topic list
     */
    private static final String TOPIC = "orchestrationservice";

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = OrchestrationServiceProducerHandler.class.getSimpleName();

    /**
     * constructor.<BR/>
     */
    public OrchestrationServiceProducerHandler(int id) {
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
