package com.pineone.icbms.so.util.messagequeue.consumer;

import com.pineone.icbms.so.util.spring.springkafka.consumer.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Consumer handler generic abstract class.<BR/>
 *
 * Created by uni4love on 2016. 12. 15..
 */
abstract public class AGenericConsumerHandler extends AConsumerHandler<String, String> implements Runnable {
    /**
     * lock
     */
    private final AtomicBoolean closed = new AtomicBoolean(false);

    /**
     * constructor.<BR/>
     */
    public AGenericConsumerHandler(int id) {
        super(id);
    }

    /**
     * handler options.<BR/>
     *
     * @return Properties
     */
    @Override
    public Map getPropertiesMap() {
        return KafkaConsumerConfig.getConsumerConfigs();
    }

    /**
     * Runnable interface implement.<BR/>
     */
    @Override
    public void run() {
        log.debug("The handler:{} thread started.", id);
        try {
            subscribe(getTopicList());
            while (!closed.get()) {
                ConsumerRecords<String, String> records = getMessage();
                log.debug("records count: {}", records.count());
                if(records == null || records.isEmpty())
                    continue;
                handle(records);
            }
        } catch (WakeupException e) {
            log.error(e.getMessage());
            //Ignore exception if closing
            if(!closed.get())
                throw e;
        } finally {
            this.close();
        }
        log.debug("The handler:{} thread ended.", id);
    }

    @Override
    public void shutdown() {
        closed.set(true);
        super.shutdown();
    }
}
