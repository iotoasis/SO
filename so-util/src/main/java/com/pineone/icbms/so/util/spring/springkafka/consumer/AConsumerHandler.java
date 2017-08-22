package com.pineone.icbms.so.util.spring.springkafka.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * KafkaConsumer handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 10..
 */
abstract public class AConsumerHandler<M> {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * countdown latch
     */
    private CountDownLatch latch = new CountDownLatch(1);

    /**
     * constructor.<BR/>
     */
    public AConsumerHandler() {
    }

    /**
     * Message consumer handle.<BR/>
     *
     * This implementation class must use "@KafkaListener" annotation.<BR/>
     *
     * @param message message object
     */
    abstract public void onMessage(M message);

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public void countDown() { latch.countDown(); }
}
