package com.pineone.icbms.so.web.tracking.springkafka;

import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
//import com.pineone.icbms.so.interfaces.messagequeue.tracking.data.TrackingService;
//import com.pineone.icbms.so.interfaces.messagequeue.tracking.data.model.TrackingEntity;
import com.pineone.icbms.so.interfaces.database.repository.TrackingRepository;
import com.pineone.icbms.so.util.conversion.JsonMapper;
import com.pineone.icbms.so.util.spring.springkafka.consumer.AConsumerHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

/**
 * ContextModel handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 10..
 */
public class TrackingConsumerHandler extends AConsumerHandler<ConsumerRecord<String, String>> {

//    @Autowired
//    TrackingService trackingService;

    @Autowired
    TrackingRepository trackingRepository;
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
//            trackingService.insert(tracking);
            trackingRepository.save(tracking);
//        } catch (IOException e) {
//            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
