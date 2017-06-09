package com.pineone.icbms.so.util.spring.springkafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * KafkaProducer handler.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
abstract public class AProducerHandler<K, V> implements ListenableFutureCallback<SendResult<String, String>> {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * kafka producer template by springkafka.
     */
    @Autowired
    private KafkaTemplate<K, V> kafkaTemplate;

    /**
     * send message to kafka.<BR/>
     *
     * @param topic   topic
     * @param key     key
     * @param message value
     */
    public void send(String topic, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        ListenableFuture<SendResult<K, V>> future = kafkaTemplate.send(topic, key, message);
        if (callback != null)
            future.addCallback(callback);
    }

    /**
     * Called when the {@link ListenableFuture} completes with success.
     * <p>Note that Exceptions raised by this method are ignored.
     *
     * @param result the result
     */
    @Override
    public void onSuccess(SendResult<String, String> result) {
        log.info("sent message='{}' with offset={}", result.getProducerRecord(),
                result.getRecordMetadata().offset());
        onCompleted(result);
    }

    /**
     * Called when the {@link ListenableFuture} completes with failure.
     * <p>Note that Exceptions raised by this method are ignored.
     *
     * @param ex the failure
     */
    @Override
    public void onFailure(Throwable ex) {
        log.error("unable to send message='{}'", ex);
        onFailed(ex);
    }

    /**
     * Called after super.onSuccess().<BR/>
     *
     * @param result the result
     */
    abstract public void onCompleted(SendResult<String, String> result);

    /**
     * Called after super.onFailure.<BR/>
     *
     * @param ex exception
     */
    abstract public void onFailed(Throwable ex);
}
