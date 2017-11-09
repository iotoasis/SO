package com.pineone.icbms.so.util;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * settings for message queue.<BR/>
 * Created by uni4love on 2016. 12. 16..
 */
@Component
public class Settings2 {
    /**
     * broker(kafka) list
     */
	public static String brokerList;// = "localhost:9092";

	@Value("${mq.broker.list}")
    public void setBrokerList(String _brokerList) {
        brokerList = _brokerList;
    }
    
	public static String getBrokerList() {
        return brokerList;
    }

    /**
     * Zookeeper list
     */
	public static String zookeeperList = "localhost:2181";
	
    public static String getZookeeperList() {
        return zookeeperList;
    }

    @Value("${mq.zookeeper.list}")
    public void setZookeeperList(String _zookeeperList) {
        zookeeperList = _zookeeperList;
    }

    //----------------------------- consumer configs
    /**
     * consumer Poll timeout
     */
    public static long pollTimeout = 3000L;

    @Value("${mq.consumer.pool_timeout}")
    public void setPollTimeout(long _pollTimeout) {
        pollTimeout = _pollTimeout;
    }
    
    public static long getPollTimeout() {
        return pollTimeout;
    }


    /**
     * ENABLE_AUTO_COMMIT_CONFIG
     */
    public static String enableAutoCommitConfig = "true";

    @Value("${mq.consumer.enable_auto_commit}")
    public void setEnableAutoCommitConfig(String _enableAutoCommitConfig) {
        enableAutoCommitConfig = _enableAutoCommitConfig;
    }

    public static String getEnableAutoCommitConfig() {
        return enableAutoCommitConfig;
    }

    
    /**
     * AUTO_COMMIT_INTERVAL_MS_CONFIG
     */
    public static String autoCommitIntervalMsConfig = "1000";

    @Value("${mq.consumer.auto_commit_interval_ms}")
    public void setAutoCommitIntervalMsConfig(String _autoCommitIntervalMsConfig) {
        autoCommitIntervalMsConfig = _autoCommitIntervalMsConfig;
    }

    public static String getAutoCommitIntervalMsConfig() {
        return autoCommitIntervalMsConfig;
    }
    
    /**
     * SESSION_TIMEOUT_MS_CONFIG
     */
    public static String sessionTimeoutMsConfig= "15000";

    @Value("${mq.consumer.session_timeout_ms}")
    public void setSessionTimeoutMsConfig(String _sessionTimeoutMsConfig) {
        sessionTimeoutMsConfig = _sessionTimeoutMsConfig;
    }

    public static String getSessionTimeoutMsConfig() {
        return sessionTimeoutMsConfig;
    }

    /**
     * AUTO_OFFSET_RESET_CONFIG
     */
    public static String autoOffsetResetConfig= "earliest";

    @Value("${mq.consumer.auto_offset_reset}")
    public void setAutoOffsetResetConfig(String _autoOffsetResetConfig) {
        autoOffsetResetConfig = _autoOffsetResetConfig;
    }

    public static String getAutoOffsetResetConfig() {
        return autoOffsetResetConfig;
    }

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
    public static String acksConfig= "1";

    @Value("${mq.producer.acks}")
    public void setAcksConfig(String _acksConfig) {
        acksConfig = _acksConfig;
    }

    public static String getAcksConfig() {
        return acksConfig;
    }

    /**
     * RETRIES_CONFIG
     */
    public static int retriesConfig = 0;

    @Value("${mq.producer.retries}")
    public void setRetriesConfig(int _retriesConfig) {
    	retriesConfig = _retriesConfig;
    }
    
    public static int getRetriesConfig() {
        return retriesConfig;
    }


    /**
     * BATCH_SIZE_CONFIG
     */
    public static int batchSizeConfig= 16384;

    @Value("${mq.producer.batch_size}")
    public void setBatchSizeConfig(int _batchSizeConfig) {
    	batchSizeConfig = _batchSizeConfig;
    }
    
    public static int getBatchSizeConfig() {
        return batchSizeConfig;
    }

    /**
     * LINGER_MS_CONFIG
     */
    public static int lingerMsConfig= 1;

    @Value("${mq.producer.linger_ms}")
    public void setLingerMsConfig(int _lingerMsConfig) {
        lingerMsConfig = _lingerMsConfig;
    }

    public static int getLingerMsConfig() {
        return lingerMsConfig;
    }


    /**
     * BUFFER_MEMORY_CONFIG
     */
    public static int bufferMemoryConfig= 33554432;

    @Value("${mq.producer.buffer_memory}")
    public void setBufferMemoryConfig(int _bufferMemoryConfig) {
    	bufferMemoryConfig = _bufferMemoryConfig;
    }
    
    public static int getBufferMemoryConfig() {
        return bufferMemoryConfig;
    }

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
    public static String deviceDriverPath= "/";

    @Value("${so.device.driver.path}")
    public void setDeviceDriverPath(String _deviceDriverPath) {
    	deviceDriverPath = _deviceDriverPath;
    }
    public static String getDeviceDriverPath() {
        return deviceDriverPath;
    }

    /**
     * Server port
     */
    public static String serverPort= "8080";

    @Value("${server.port}")
    public void setServerPort(String port) {
    	serverPort = port;
    }
    public static String getServerPort() {
        return serverPort;
    }

    /**
     * Server contextPath
     */
    public static String contextPath= "/so";

    @Value("${server.contextPath}")
    public void setContextPath(String path) {
    	contextPath = path;
    }
    public static String getContextPath() {
        return contextPath;
    }

    //
    public static String sdaConnectionUri;
    @Value("${sda.connection.uri:null}")
	public void setSdaConnectionUri(String sdaConnectionUri) {
		Settings2.sdaConnectionUri = sdaConnectionUri;
	}

    public static String getSdaConnectionUri() {
		return sdaConnectionUri;
	}

    //
    public static String siConnectionUri;
	@Value("${si.connection.uri:null}")
	public void setSiConnectionUri(String siConnectionUri) {
		Settings2.siConnectionUri = siConnectionUri;
	}
    public static String getSiConnectionUri() {
		return siConnectionUri;
	}

    //
    public static String soConnectionUri;
    @Value("${so.connection.uri:null}")
    public void setSoConnectionUri(String soConnectionUri) {
		Settings2.soConnectionUri = soConnectionUri;
	}

	public static String getSoConnectionUri() {
		return soConnectionUri;
	}

}
