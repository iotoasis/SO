package com.pineone.icbms.so.util.spring.springkafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

/**
 * Producer config object.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
@Configuration
@EnableKafka
public class ProducerConfig {
    /**
     * return KafkaTemplate.(for kafka producer)<BR/>
     *
     * @return KafkaTemplate
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * return ProducerFactory.<BR/>
     *
     * @return ProducerFactory
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * return Producer configs.<BR/>
     *
     * @return Map object for configs
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        return KafkaProducerConfig.getProducerConfigs();
    }
}
