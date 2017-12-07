package com.pineone.icbms.so.util.spring.springkafka.consumer;


import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.util.Settings2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * support object for KafkaConsumer.<BR/>
 * <p>
 * Created by uni4love on 2017. 4. 10..
 */
@Component
abstract public class AConsumerConfig {
    /**
     * logger
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * return KafkaListenerContainerFactory.<BR/>
     *
     * @return KafkaListenerContainerFactory
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        log.debug("kafkaListenerContainerFactory()");
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(Settings.HANDLER_COUNT);
        factory.getContainerProperties().setPollTimeout(Settings2.getPollTimeout());
        return factory;
    }

    /**
     * return Consumer factory.<BR/>
     *
     * @return ConsumerFactory
     */
    @Bean
    public ConsumerFactory consumerFactory() {
        log.debug("consumerFactory()");
        return new DefaultKafkaConsumerFactory(consumerConfigs());
    }

    /**
     * return Producer configs.<BR/>
     *
     * @return Map object for configs
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        log.debug("consumerConfigs()");
        Map<String, Object> configs = KafkaConsumerConfig.getConsumerConfigs();
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
        return configs;
    }

    /**
     * return group id.<BR/>
     *
     * @return group id
     */
    abstract public String getGroupId();
}
