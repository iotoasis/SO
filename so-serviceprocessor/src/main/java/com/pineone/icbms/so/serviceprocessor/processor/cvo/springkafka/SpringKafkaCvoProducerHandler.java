package com.pineone.icbms.so.serviceprocessor.processor.cvo.springkafka;

import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.spring.springkafka.producer.AProducerHandler;
import org.springframework.kafka.support.SendResult;

/**
 * 'compositevirtualobject' topic producer.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
public class SpringKafkaCvoProducerHandler extends AProducerHandler<String, String> {
   /**
     * send message to kafka.<BR/>
     *
     * @param key     key
     * @param message value
     */
    public void send(String key, String message) {
        send(Settings.TOPIC_COMPOSITE_VIRTUAL_OBJECT, key, message, this);
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
