package com.pineone.icbms.so.serviceprocessor.processor.cvo.messagequeue.producer;

import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.messagequeue.producer.AGenericProducerHandler;

/**
 * CompositeVirtualObjectForDB Producer handler.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
public class CvoProducerHandler extends AGenericProducerHandler {
    /**
     * topic list
     */
    private static final String TOPIC = Settings.TOPIC_COMPOSITE_VIRTUAL_OBJECT;

    /**
     * kafka producer group id by class name.<BR/>
     */
    private static final String GROUP_ID = CvoProducerHandler.class.getSimpleName();

    /**
     * constructor.<BR/>
     */
    public CvoProducerHandler(int id) {
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
