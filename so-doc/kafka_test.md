## kafka 실행 테스트 (window)

####(1) Zookeeper 구동
 
```
cd ...\kafka_2.11-0.10.2.0\bin\windows
zookeeper-server-start.bat ../../config/zookeeper.properties
```

####(2) kafka 구동

```
cd ...\kafka_2.11-0.10.2.0\bin\windows
kafka-server-start.bat ../../config/server.properties
```

#### (3) Topic 생성
- topic name : "test" ; 테스트 용도

```
cd ...\kafka_2.11-0.10.2.0\bin\windows
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

- 다음과 같은 메시지 출력 : Created topic "test".

```
kafka-topics.bat --list --zookeeper localhost:2181
```

- 다음과 같은 메시지 출력 : test

#### (4) Test 진행
- producer(send Message)

```
cd ...\kafka_2.11-0.10.2.0\bin\windows
kafka-console-producer.bat --broker-list localhost:9092 --topic test
```

- consumer(get Message)

```
cd ...\kafka_2.11-0.10.2.0\bin\windows
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
```



## Kafka 실행 테스트 (shell)

####(1) Zookeeper 구동 

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

####(2) kafka 구동

```
bin/kafka-server-start.sh config/server.properties
```
		
#### (3) Topic 생성
- topic name : "test" ; 테스트 용도

```
# bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
# bin/kafka-topics.sh --list --zookeeper localhost:2181
```
			
#### (4) Test 진행
- producer(send Message)

```
# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```

- consumer(get Message)

```
# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
```	
	
