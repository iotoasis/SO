package com.pineone.icbms.so.util.messagequeue.consumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pineone.icbms.so.util.Settings2;
import com.pineone.icbms.so.util.property.PropertyUtils;
import com.pineone.icbms.so.util.spring.springkafka.consumer.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Consumer handler generic abstract class.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
abstract public class AGenericConsumerHandler2<K, V>
        implements IConsumerHandler<ConsumerRecords<K, V>, ConsumerRecords<K, V>>, Runnable {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

//    /**
//     * handler poll timeout.<BR/>
//     * Long.MAX_VALUE was blocked.
//     */
//    protected static long POLL_TIMEOUT = Settings.POLL_TIMEOUT;

    /**
     * handler running flag
     */
    protected boolean running = true;

    /**
     * id
     */
    protected int id;

    /**
     * Kafka handler<BR/>
     */
    protected KafkaConsumer<K, V> consumer;

    /**
     * lock
     */
    private final AtomicBoolean closed = new AtomicBoolean(false);

    /**
     * object mapper
     */
    private ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * constructor.<BR/>
     */
    public AGenericConsumerHandler2() {
    }

    /**
     * constructor.<BR/>
     */
    public AGenericConsumerHandler2(int id) {
        this.id = id;
        Properties properties = initProperties(KafkaConsumerConfig.getConsumerConfigs());
        consumer = new KafkaConsumer<>(properties);
    }

    /**
     * initialize properties<BR/>
     *
     * @return Properties
     */
    Properties initProperties(Map propertiesMap) {
        Properties properties = PropertyUtils.getProperties(propertiesMap);
        //default properties
        if (!properties.containsKey(ConsumerConfig.GROUP_ID_CONFIG))
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
        return properties;
    }

    @Override
    public void subscribe(Collection<String> topics) {
        consumer.subscribe(topics);
    }

    @Override
    public ConsumerRecords<K, V> getMessage() {
        return consumer.poll(Settings2.getPollTimeout());
    }

    @Override
    public void close() {
        consumer.close();
    }

    /**
     * when shutdown, to do..<BR/>
     */
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
        log.info("The handler:{} shutdown.", id);
    }

    /**
     * Runnable interface implement.<BR/>
     */
    @Override
    public void run() {
        log.debug("The handler:{} thread started.", id);
        process();
        log.debug("The handler:{} thread ended.", id);
    }

    /**
     * consumer handle process
     */
    public void process() {
        try {
            subscribe(getTopicList());
            while (!closed.get()) {
                ConsumerRecords<K, V> records = getMessage();
                log.debug("records count: {}", records.count());
                if (records == null || records.isEmpty())
                    continue;
                handle(records);
            }
        } catch (WakeupException e) {
            log.error(e.getMessage());
            //Ignore exception if closing
            if (!closed.get())
                throw e;
        } finally {
            this.close();
        }
    }

    /**
     * set running flag.<BR/>
     *
     * @param running running flag
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * return running flag.<BR/>
     *
     * @return running flag
     */
    public boolean getRunning() {
        return running;
    }

    /**
     * return group id.<BR/>
     *
     * @return group id
     */
    abstract public String getGroupId();

    /**
     * return topic list.<BR/>
     *
     * @return topic list
     */
    abstract public List<String> getTopicList();

    /**
     * handle method.<BR/>
     *
     * @param records ConsumerRecords
     */
    public abstract void handle(ConsumerRecords<K, V> records);

    /**
     * handle method.<BR/>
     *
     * @param record ConsumerRecords
     */
    public abstract void handle(ConsumerRecord<K, V> record);
}
