package com.pineone.icbms.so.util.messagequeue.producer;

import com.pineone.icbms.so.util.property.PropertyUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * abstract class for Kafka Producer.<BR/>
 *
 * Created by uni4love on 2016. 1. 6..
 */
abstract public class AProducerHandler<K, V> implements IProducerHandler<Future<RecordMetadata>, K, V> /*, Runnable*/ {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * id
     */
    private final int id;

    /**
     * Kafka producer<BR/>
     */
    protected KafkaProducer<K, V> producer;

    /**
     * constructor.<BR/>
     */
    public AProducerHandler(int id) {
        this.id = id;
    }

    /**
     * initialize properties<BR/>
     *
     * @return Properties
     */
    Properties initProperties(Map propertiesMap) {
        Properties properties = PropertyUtils.getProperties(propertiesMap);
        return properties;
    }

    /**
     * send a record to producer.<BR/>
     *
     * @param key key
     * @param value value
     */
    @Override
    public Future<RecordMetadata> send(K key, V value) {
        ProducerRecord<K, V> producerRecord = new ProducerRecord<>(getTopic(), key, value);
        if(producer == null) {
            producer = createProducer();
        }
        return producer.send(producerRecord);
    }

    /**
     * return Properties map.<BR/>
     *
     * @return Map
     */
    abstract public Map getProducerPropertiesMap();

    /**
     * return topic.<BR/>
     *
     * @return topic
     */
    abstract public String getTopic();

    /**
     * create and return producer.<BR/>
     *
     * @return KafkaProducer
     */
    abstract public KafkaProducer<K, V> createProducer();
}
