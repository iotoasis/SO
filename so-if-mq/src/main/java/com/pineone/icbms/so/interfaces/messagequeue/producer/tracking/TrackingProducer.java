package com.pineone.icbms.so.interfaces.messagequeue.producer.tracking;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.util.messagequeue.producer.DefaultProducerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jonghee on 2017-05-17.
 */
public class TrackingProducer {

    /**
     * logger
     */
    protected static Logger log = LoggerFactory.getLogger(TrackingProducer.class);

    public static void send(TrackingEntity tracking) {
        DefaultProducerHandler trackingHandler = new DefaultProducerHandler(0, "tracking");
        trackingHandler.send(tracking);
        trackingHandler.close();
    }

    // process 를 추가하는 경우 사용
//    public static TrackingEntity send(TrackingEntity tracking, String... process) {
//        //tracking.addProcess(process);
//        // step, id, name, info
//        for (int i = 0; i < process.length; i++) {
//            if (i == 0) tracking.setProcess(process[i]);
//            if (i == 3) tracking.setProcessMethod(process[i]);
//            if (i == 2) tracking.setProcessName(process[i]);
//            if (i == 1) tracking.setProcessId(process[i]);
//            if (i > 3) break;
//        }
//
//        DefaultProducerHandler trackingHandler = new DefaultProducerHandler(0, "tracking");
//        trackingHandler.send(tracking);
//        trackingHandler.close();
//
//        return tracking;
//    }

}
