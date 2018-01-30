package com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.springkafka;

import com.pineone.icbms.so.serviceprocessor.processor.devicecontrol.messagequeue.consumer.DeviceControlConsumerHandler;
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
public class SpringKafkaDeviceControlConsumerHandler extends AConsumerHandler<ConsumerRecord<String, String>> {
    /**
     * DatabaseManager interface
     */
    @Autowired
    protected DatabaseManager databaseManager;

    /**
     * consumer handler
     */
    DeviceControlConsumerHandler consumerHandler;

    /**
     * constructor.<BR/>
     */
    public SpringKafkaDeviceControlConsumerHandler() {
    }

    /**
     * Message consumer handle.<BR/>
     *
     * @param record message object
     */
    @KafkaListener(topics = "devicecontrol")
    public void onMessage(ConsumerRecord<String, String> record) {
        if (consumerHandler == null)
            consumerHandler = new DeviceControlConsumerHandler(databaseManager);
        consumerHandler.handle(record);

        super.countDown();
    }
}
