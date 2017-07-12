# SO 서버 설정

SO 서버 설정방법을 설명합니다.

설정은 properties 파일로 작성되며 so-web 폴더의 resources 폴더에 존재합니다. 

```
application.properties
```
<br>
<br>

설정항목별 의미는 아래와 같습니다.


Item              | Description
----------------- | --------------------------
server.context-path  | 서버 main-path 설정 
server.port  | 서버 포트 설정 
spring.jackson.time-zone| 서버 시간 설정 (Asia/Seoul 추천)
mq.broker.list | 메세지 큐 포트 설정 
mq.zookeeper.list | 쥬키퍼 포트 설정 
spring.datasource.url | DataBase URL
spring.datasource.username | DB 계정 정보
spring.datasource.password | DB Password
kafka.topic.xxxx | kafka Topic 정보 (n개)

<br>
<br>

아래는 설정파일 샘플입니다.

```
spring.data.mongodb.repositories.enabled=true
server.context-path=/so
server.port=10012
spring.jackson.time-zone=Asia/Seoul
mq.broker.list=localhost:9999
mq.zookeeper.list=localhost:8888
spring.datasource.url=jdbc:mysql://localhost/so?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=user
spring.datasource.password=pass
kafka.topic.test = test 
```

<br>
<br>
