package com.pineone.icbms.so.serviceprocessor.processor.virtualobject.springkafka;

import com.pineone.icbms.so.serviceprocessor.repository.database.DatabaseManager;
import com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.consumer.VirtualObjectConsumerHandler;
import com.pineone.icbms.so.util.spring.springkafka.consumer.AConsumerHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * ContextModel handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 10..
 */
public class SpringKafkaVirtualObjectConsumerHandler extends AConsumerHandler<ConsumerRecord<String, String>> {
    /**
     * DatabaseManager interface
     */
    @Autowired
    protected DatabaseManager databaseManager;

    /**
     * consumer handler
     */
    VirtualObjectConsumerHandler consumerHandler;

    /**
     * constructor.<BR/>
     */
    public SpringKafkaVirtualObjectConsumerHandler() {
    }

    /**
     * Message consumer handle.<BR/>
     *
     * @param record message object
     */
    @KafkaListener(topics = "virtualobject")
    public void onMessage(ConsumerRecord<String, String> record) {
        if (consumerHandler == null)
            consumerHandler = new VirtualObjectConsumerHandler(databaseManager);
        consumerHandler.handle(record);
    }
}
