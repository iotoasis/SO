package com.pineone.icbms.so.util.spring.springkafka.producer;

import com.pineone.icbms.so.util.Settings2;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring config for Kafka.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
@Component
public class KafkaProducerConfig {
    /**
     * kafka producer configs.<BR/>
     *
     * @return Map object for configs
     */
    public static Map<String, Object> getProducerConfigs() {
        Map<String, Object> keyValueMap = new HashMap<>();
        keyValueMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Settings2.getBrokerList());
        keyValueMap.put(ProducerConfig.ACKS_CONFIG, Settings2.getAcksConfig());
        keyValueMap.put(ProducerConfig.RETRIES_CONFIG, Settings2.getRetriesConfig());
        keyValueMap.put(ProducerConfig.BATCH_SIZE_CONFIG, Settings2.getBatchSizeConfig());
        keyValueMap.put(ProducerConfig.LINGER_MS_CONFIG, Settings2.getLingerMsConfig());
        keyValueMap.put(ProducerConfig.BUFFER_MEMORY_CONFIG, Settings2.getBufferMemoryConfig());
        keyValueMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Settings2.KEY_SERIALIZER_CLASS_CONFIG);
        keyValueMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Settings2.VALUE_SERIALIZER_CLASS_CONFIG);
        return keyValueMap;
    }

}
