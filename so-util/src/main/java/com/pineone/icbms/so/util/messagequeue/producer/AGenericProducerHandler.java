package com.pineone.icbms.so.util.messagequeue.producer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pineone.icbms.so.util.spring.springkafka.producer.KafkaProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * Producer handler generic abstract class.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
abstract public class AGenericProducerHandler<T> extends AProducerHandler<String, String> {

    private String topic;

    /**
     * constructor.<BR/>
     */
    public AGenericProducerHandler(int id) {
        super(id);
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    /**
     * producer options.<BR/>
     *
     * @return Properties
     */
    @Override
    public Map getProducerPropertiesMap() {
        return KafkaProducerConfig.getProducerConfigs();
    }

    /**
     * send a record to producer.<BR/>
     *
     * @param value value
     */
    public Future<RecordMetadata> send(String value) {
        return this.send(this.topic/*getTopic()*/, value);
    }

    public Future<RecordMetadata> send(T value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return this.send(this.topic/*getTopic()*/, objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * close the producer.
     */
    @Override
    public void close() {
        producer.close();
    }

    @Override
    public KafkaProducer<String, String> createProducer() {
        return new KafkaProducer<String, String>(initProperties(getProducerPropertiesMap()));
    }
}
