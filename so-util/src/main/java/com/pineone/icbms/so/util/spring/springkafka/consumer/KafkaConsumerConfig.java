package com.pineone.icbms.so.util.spring.springkafka.consumer;

import com.pineone.icbms.so.util.Settings2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer config object.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
@Component
public class KafkaConsumerConfig {

    /**
     * kafka consumer configs.<BR/>
     *
     * @return Map object for configs
     */
    @Bean
    public static Map<String, Object> getConsumerConfigs() {
        Map<String, Object> keyValueMap = new HashMap<>();
        keyValueMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Settings2.getBrokerList());
        keyValueMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Settings2.getEnableAutoCommitConfig());
        keyValueMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, Settings2.getAutoCommitIntervalMsConfig());
        keyValueMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, Settings2.getSessionTimeoutMsConfig());
        keyValueMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Settings2.getAutoOffsetResetConfig());
        keyValueMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Settings2.KEY_DESERIALIZER_CLASS_CONFIG);
        keyValueMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Settings2.VALUE_DESERIALIZER_CLASS_CONFIG);
        return keyValueMap;
    }
}
