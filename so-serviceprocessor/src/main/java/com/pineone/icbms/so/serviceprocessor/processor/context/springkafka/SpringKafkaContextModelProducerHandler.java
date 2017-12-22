package com.pineone.icbms.so.serviceprocessor.processor.context.springkafka;

import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.spring.springkafka.producer.AProducerHandler;
import org.springframework.kafka.support.SendResult;

/**
 * 'contextmodel' topic producer.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
public class SpringKafkaContextModelProducerHandler extends AProducerHandler<String, String> {

    /**
     * send message to kafka.<BR/>
     *
     * @param key     key
     * @param message value
     */
    public void send(String key, String message) {
        send(Settings2.TOPIC_CONTEXT_MODEL, key, message, this);
    }

    /**
     * Called after super.onSuccess().<BR/>
     *
     * @param result the result
     */
    @Override
    public void onCompleted(SendResult<String, String> result) {

    }

    /**
     * Called after super.onFailure.<BR/>
     *
     * @param ex exception
     */
    @Override
    public void onFailed(Throwable ex) {

    }
}
