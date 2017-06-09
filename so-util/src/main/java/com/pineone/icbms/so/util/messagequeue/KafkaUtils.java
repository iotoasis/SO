package com.pineone.icbms.so.util.messagequeue;


import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * This is Utility for support Kafka.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public class KafkaUtils {

    /**
     * return Map from ConsumerRecord\<String, String\><BR/>
     *
     * @param record ConsumerRecord
     * @return Map
     */
    public static Map getMapFromConsumerRecord(ConsumerRecord<String, String> record) {
        Map<String, Object> data = new HashMap<>();
        data.put("partition", record.partition());
        data.put("offset", record.offset());
        data.put("topic", record.topic());
        data.put("key", record.key());
        data.put("value", record.value());
        return data;
    }
}
