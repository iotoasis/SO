package com.pineone.icbms.so.util.spring.springkafka.producer;

import com.pineone.icbms.so.util.Settings2;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring config for Kafka.<BR/>
 *
 * Created by uni4love on 2017. 4. 10..
 */
@Component
public class KafkaProducerConfig {
	protected static Logger log = LoggerFactory.getLogger(KafkaProducerConfig.class);
	
    /**
     * kafka producer configs.<BR/>
     *
     * @return Map object for configs
     */
    public static Map<String, Object> getProducerConfigs() {
    	checkBrokerEnabled();
    	
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

    // BROKER가 동작중인지 체크
    static boolean checkBrokerEnabled(){
    	String serverUrl = Settings2.getBrokerList(); //Broker정보 읽어오기
    	String params[] = serverUrl.split(":");
    	String host = params[0];
    	Integer port = Integer.valueOf(params[1]);
    	try {
    		//서버 검사
			Socket sock = new Socket(host, port);
			sock.close();
			log.debug("checkBrokerEnabled: Kafka is running ==");
			return true; //정상 실행
		} catch (IOException e) {
			e.printStackTrace();
		}
    	log.error("checkBrokerEnabled: == Kafka is not running ==");
    	System.exit(1); //시스템 종료
    	return false;
    }
}
