package com.pineone.icbms.so.web.tracking.springkafka;


import com.pineone.icbms.so.util.spring.springkafka.consumer.AConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * support object for KafkaConsumer.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
@Configuration
@EnableKafka
public class TrackingConsumerConfig extends AConsumerConfig {
    /**
     * kafka handler group id by class name.<BR/>
     */
    private static final String GROUP_ID = TrackingConsumerHandler.class.getSimpleName();

    /**합계 8
     * return group id.<BR/>
     *
     * @return group id
     */
    @Override
    public String getGroupId() {
        return GROUP_ID;
    }

    /**
     * return listener class.<BR/>
     *
     * @return ContextModelConsumerHandler
     */
    @Bean
    public TrackingConsumerHandler trackingConsumerHandler() {
        return new TrackingConsumerHandler();
    }
}
