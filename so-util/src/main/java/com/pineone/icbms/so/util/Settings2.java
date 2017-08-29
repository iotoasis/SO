package com.pineone.icbms.so.util;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * settings for message queue.<BR/>
 * Created by uni4love on 2016. 12. 16..
 */
public class Settings2 {
    /**
     * broker(kafka) list
     */
    @Value("${mq.broker.list}")
    public static String brokerList = "localhost:9092";

    /**
     * Zookeeper list
     */
    @Value("${mq.zookeeper.list}")
    public static String zookeeperList = "localhost:2181";

    //----------------------------- consumer configs
    /**
     * consumer Poll timeout
     */
    @Value("${mq.consumer.pool_timeout}")
    public static long pollTimeout = 3000L;

    /**
     * ENABLE_AUTO_COMMIT_CONFIG
     */
    @Value("${mq.consumer.enable_auto_commit}")
    public static String enableAutoCommitConfig = "true";

    /**
     * AUTO_COMMIT_INTERVAL_MS_CONFIG
     */
    @Value("${mq.consumer.auto_commit_interval_ms}")
    public static String autoCommitIntervalMsConfig = "1000";

    /**
     * SESSION_TIMEOUT_MS_CONFIG
     */
    @Value("${mq.consumer.session_timeout_ms}")
    public static String sessionTimeoutMsConfig= "15000";

    /**
     * AUTO_OFFSET_RESET_CONFIG
     */
    @Value("${mq.consumer.auto_offset_reset}")
    public static String autoOffsetResetConfig= "earliest";

    /**
     * KEY_DESERIALIZER_CLASS_CONFIG
     */
    public static String KEY_DESERIALIZER_CLASS_CONFIG = StringDeserializer.class.getName();

    /**
     * VALUE_DESERIALIZER_CLASS_CONFIG
     */
    public static String VALUE_DESERIALIZER_CLASS_CONFIG = StringDeserializer.class.getName();

    //----------------------------- producer configs
    /**
     * ACKS_CONFIG
     */
    @Value("${mq.producer.acks}")
    public static String acksConfig= "1";

    /**
     * RETRIES_CONFIG
     */
    @Value("${mq.producer.retries}")
    public static int retriesConfig = 0;

    /**
     * BATCH_SIZE_CONFIG
     */
    @Value("${mq.producer.batch_size}")
    public static int batchSizeConfig= 16384;

    /**
     * LINGER_MS_CONFIG
     */
    @Value("${mq.producer.linger_ms}")
    public static int lingerMsConfig= 1;

    /**
     * BUFFER_MEMORY_CONFIG
     */
    @Value("${mq.producer.buffer_memory}")
    public static int bufferMemoryConfig= 33554432;

    /**
     * KEY_SERIALIZER_CLASS_CONFIG
     */
    public static String KEY_SERIALIZER_CLASS_CONFIG = StringSerializer.class.getName();

    /**
     * VALUE_SERIALIZER_CLASS_CONFIG
     */
    public static String VALUE_SERIALIZER_CLASS_CONFIG = StringSerializer.class.getName();


    //----------------------------- common configs
    /**
     * kafka topic: contextmodel<BR/>
     */
    public static String TOPIC_CONTEXT_MODEL = "contextmodel";

    /**
     * kafka topic: orchestrationservice<BR/>
     */
    public static String TOPIC_ORCHESTRATION_SERVICE = "orchestrationservice";

    /**
     * kafka topic: virtual object<BR/>
     */
    public static String TOPIC_VIRTUAL_OBJECT = "virtualobject";

    /**
     * kafka topic: devicecontrol<BR/>
     */
    public static String TOPIC_DEVICE_CONTROL = "devicecontrol";

    /**
     * kafka topic: logging
     */
    public static String TOPIC_LOGGING = "logging";

    /**
     * each serviceprocessor handler count
     */
    public static int HANDLER_COUNT = 1;

    /**
     * CONTEXTMODEL serviceprocessor handler count
     */
    public static int HANDLER_CONTEXTMODEL_COUNT = HANDLER_COUNT;

    /**
     * ORCHESTRATIONSERVICE serviceprocessor handler count
     */
    public static int HANDLER_ORCHESTRATIONSERVICE_COUNT = HANDLER_COUNT;

    /**
     * VIRTUALOBJECT serviceprocessor handler count
     */
    public static int HANDELR_VIRTUALOBJECT_COUNT = HANDLER_COUNT;

    /**
     * DEVICECONTROL serviceprocessor handler count
     */
    public static int HANDLER_DEVICECONTROL_COUNT = HANDLER_COUNT;

    /**
     * class path for class loader
     */
    @Value("${so.device.driver.path}")
    public static String deviceDriverPath= "/";

    public static String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public static String getZookeeperList() {
        return zookeeperList;
    }

    public void setZookeeperList(String zookeeperList) {
        this.zookeeperList = zookeeperList;
    }

    public static long getPollTimeout() {
        return pollTimeout;
    }

    public void setPollTimeout(long pollTimeout) {
        this.pollTimeout = pollTimeout;
    }

    public static String getEnableAutoCommitConfig() {
        return enableAutoCommitConfig;
    }

    public void setEnableAutoCommitConfig(String enableAutoCommitConfig) {
        this.enableAutoCommitConfig = enableAutoCommitConfig;
    }

    public static String getAutoCommitIntervalMsConfig() {
        return autoCommitIntervalMsConfig;
    }

    public void setAutoCommitIntervalMsConfig(String autoCommitIntervalMsConfig) {
        this.autoCommitIntervalMsConfig = autoCommitIntervalMsConfig;
    }

    public static String getSessionTimeoutMsConfig() {
        return sessionTimeoutMsConfig;
    }

    public void setSessionTimeoutMsConfig(String sessionTimeoutMsConfig) {
        this.sessionTimeoutMsConfig = sessionTimeoutMsConfig;
    }

    public static String getAutoOffsetResetConfig() {
        return autoOffsetResetConfig;
    }

    public void setAutoOffsetResetConfig(String autoOffsetResetConfig) {
        this.autoOffsetResetConfig = autoOffsetResetConfig;
    }

    public static String getAcksConfig() {
        return acksConfig;
    }

    public void setAcksConfig(String acksConfig) {
        this.acksConfig = acksConfig;
    }

    public static int getRetriesConfig() {
        return retriesConfig;
    }

    public void setRetriesConfig(int retriesConfig) {
        this.retriesConfig = retriesConfig;
    }

    public static int getBatchSizeConfig() {
        return batchSizeConfig;
    }

    public void setBatchSizeConfig(int batchSizeConfig) {
        this.batchSizeConfig = batchSizeConfig;
    }

    public static int getLingerMsConfig() {
        return lingerMsConfig;
    }

    public void setLingerMsConfig(int lingerMsConfig) {
        this.lingerMsConfig = lingerMsConfig;
    }

    public static int getBufferMemoryConfig() {
        return bufferMemoryConfig;
    }

    public void setBufferMemoryConfig(int bufferMemoryConfig) {
        this.bufferMemoryConfig = bufferMemoryConfig;
    }

    public static String getDeviceDriverPath() {
        return deviceDriverPath;
    }

    public void setDeviceDriverPath(String deviceDriverPath) {
        this.deviceDriverPath = deviceDriverPath;
    }
}
