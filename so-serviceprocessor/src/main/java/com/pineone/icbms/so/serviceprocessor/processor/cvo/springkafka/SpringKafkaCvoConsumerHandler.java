package com.pineone.icbms.so.serviceprocessor.processor.cvo.springkafka;

import com.pineone.icbms.so.serviceprocessor.processor.cvo.messagequeue.consumer.CvoConsumerHandler;
import com.pineone.icbms.so.serviceutil.interfaces.database.DatabaseManager;
import com.pineone.icbms.so.util.spring.springkafka.consumer.AConsumerHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * ContextModel handler.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 10..
 */
public class SpringKafkaCvoConsumerHandler extends AConsumerHandler<ConsumerRecord<String, String>> {
    /**
     * DatabaseManager interface
     */
    @Autowired
    protected DatabaseManager databaseManager;

    /**
     * consumer handler
     */
    CvoConsumerHandler consumerHandler;

    /**
     * constructor.<BR/>
     */
    public SpringKafkaCvoConsumerHandler() {
    }

    /**
     * Message consumer handle.<BR/>
     *
     * @param record message object
     */
    @KafkaListener(topics = "compositevirtualobject")
    public void onMessage(ConsumerRecord<String, String> record) {
        if (consumerHandler == null)
            consumerHandler = new CvoConsumerHandler(databaseManager);
        consumerHandler.handle(record);

        super.countDown();
    }
}
