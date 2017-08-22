package com.pineone.icbms.so.serviceprocessor.processor.tracking.messagequeue.consumer;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.serviceutil.interfaces.database.IDatabaseManager;
import com.pineone.icbms.so.util.conversion.JsonMapper;
import com.pineone.icbms.so.util.spring.springkafka.consumer.AConsumerHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * ContextModel handler.<BR/>
 * <p>
 * Created by jonghee on 2017-05-17.
 */
public class TrackingConsumerHandler extends AConsumerHandler<ConsumerRecord<String, String>> {

    @Autowired
    IDatabaseManager databaseManager;
    /**
     * constructor.<BR/>
     */
    public TrackingConsumerHandler() {
    }

    /**
     * Message consumer handle.<BR/>
     *
     * @param record     message object
     */
    @KafkaListener(topics = "tracking")
    public void onMessage(ConsumerRecord<String, String> record) {
        log.debug("received message: {}", record);
        try {
            TrackingEntity tracking = JsonMapper.readJsonObject(record.value(), TrackingEntity.class);

            super.countDown();

//            trackingService.insert(tracking);
            // JPA
            //trackingRepository.save(tracking);
            databaseManager.createTracking(tracking);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
