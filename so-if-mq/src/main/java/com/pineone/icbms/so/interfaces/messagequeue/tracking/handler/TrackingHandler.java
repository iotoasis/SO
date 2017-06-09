package com.pineone.icbms.so.interfaces.messagequeue.tracking.handler;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jonghee on 2017-05-17.
 */
public class TrackingHandler {

    /**
     * logger
     */
    protected static Logger log = LoggerFactory.getLogger(TrackingHandler.class);

    public static void send(TrackingEntity tracking) {
        DefaultProducerHandler trackingHandler = new DefaultProducerHandler(0, "tracking");
        trackingHandler.send(tracking);
        trackingHandler.close();
    }

    // process 를 추가하는 경우 사용
    public static TrackingEntity send(TrackingEntity tracking, String process) {
        //tracking.addProcess(process);
        tracking.setProcess(process);

        DefaultProducerHandler trackingHandler = new DefaultProducerHandler(0, "tracking");
        trackingHandler.send(tracking);
        trackingHandler.close();

        return tracking;
    }

}
