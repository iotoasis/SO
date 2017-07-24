# SO 서버 설정

SO 서버 설정방법을 설명합니다.

설정은 properties.yml 파일로 작성되며 so-web 폴더의 conf 폴더에 존재합니다.

```
개발용:
application-dev.yml
운영용:
application-product.yml
```
<br>
<br>

설정항목별 의미는 아래와 같습니다.


Item              | Description
----------------- | --------------------------
server: | 
	port:        | 서버 포트 설정 
	contextPath  | 서버 main-path 설정 
spring: | 
  datasource: | 
    url:      | DataBase URL
    username: | DB 계정 정보
    password: | DB Password
    driverClassName: | DB Drivet class name
  jackson: | 
    time-zone: | 서버 시간 설정 (Asia/Seoul 추천)
    mapper: | 
      sort-properties-alphabetically: true | json entities sort order
mq: | 
  broker.list: localhost:9092 | 메세지 큐 포트 설정 
  zookeeper.list: localhost:2181 | 쥬키퍼 포트 설정 
  producer: | 
    acks: 2	| ACKS_CONFIG
    retries: 0	| RETRIES_CONFIG
    batch_size: 16384 | BATCH_SIZE_CONFIG
    linger_ms: 1 | LINGER_MS_CONFIG
    buffer_memory: 33554432 | BUFFER_MEMORY_CONFIG SIZE
  consumer: | 
    pool_timeout: 4000 | POLL_TIMEOUT
    enable_auto_commit: false | ENABLE_AUTO_COMMIT_CONFIG
    auto_commit_interval_ms: 1000 | AUTO_COMMIT_INTERVAL_MS_CONFIG
    session_timeout_ms: 15000 | SESSION_TIMEOUT_MS_CONFIG
    auto_offset_reset: earliest | AUTO_OFFSET_RESET_CONFIG
	  
<br>
<br>

아래는 설정파일 샘플입니다.

```
server:
  port: 8080
  contextPath: /so

logging:
  config: file:/svc/apps/so/so/so-web/conf/logback-web.xml

so:
  serviceprocessor:
    contextmodel.enable: true
    orchestrationservice.enable: true
    virtualobject.enable: true
    devicecontrol.enable: true
  scheduler.enable: true
  device.driver.path: /svc/apps/so/so/driver

mq:
  broker.list: localhost:9092
  zookeeper.list: localhost:2181

  producer:
    acks: 2
    retries: 0
    batch_size: 16384
    linger_ms: 1
    buffer_memory: 33554432

  consumer:
    pool_timeout: 4000
    enable_auto_commit: false
    auto_commit_interval_ms: 1000
    session_timeout_ms: 15000
    auto_offset_reset: earliest

banner:
  location: banner.txt
  charset: UTF-8
  
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/so?useUnicode=true&characterEncoding=utf-8
    username: user
    password: pass
    driverClassName: com.mysql.jdbc.Driver

  jackson:
    time-zone: Asia/Seoul
    mapper:
      sort-properties-alphabetically: true
```

<br>
<br>
