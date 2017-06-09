package com.pineone.icbms.so.util.messagequeue.producer;

/**
 * Producer handler interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public interface IProducerHandler<T, K, V> {
    /**
     * send a record to producer.<BR/>
     * @param key key
     * @param value value for key
     * @return Future<RecordMetadata>
     */
    T send(K key, V value);

    /**
     * close the producer.
     */
    void close();
}
