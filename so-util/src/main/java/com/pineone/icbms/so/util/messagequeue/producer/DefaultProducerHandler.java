package com.pineone.icbms.so.util.messagequeue.producer;

/**
 * Producer handler.<BR/>
 * <p>
 * Created by uni4love on 2016. 12. 15..
 */
//@Component
public class DefaultProducerHandler extends AGenericProducerHandler<Object> {
    /**
     * topic list
     */
    //private static final String TOPIC = "logging";

    private int id = 0;

    /**
     * kafka producer group id by class name.<BR/>
     */
    private final String GROUP_ID = getClass().getSimpleName();

    /**
     * constructor.<BR/>
     */
    public DefaultProducerHandler(int id) {
        super(id);
    }

    /**
     * constructor<BR/>
     *
     * @param id    id
     * @param topic topic
     */
    public DefaultProducerHandler(int id, String topic) {
        super(id);
        super.setTopic(topic);
    }

    /**
     * return topic.<BR/>
     *
     * @return topic
     */
    @Override
    public String getTopic() {
        return super.getTopic()/*TOPIC*/;
    }
}
