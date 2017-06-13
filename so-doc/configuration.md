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
spring.data.mongodb.database=so     | 데이터 베이스 이름
spring.data.mongodb.host    | 데이터베이스 호스트명
spring.data.mongodb.port    | 데이터베이스 포트번호
server.context-path  | 서버 main-path 설정 
server.port  | 서버 포트 설정 
spring.jackson.time-zone| 서버 시간 설정 (Asia/Seoul 추천)



<br>
<br>

아래는 설정파일 샘플입니다.

```
spring.data.mongodb.database=so
spring.data.mongodb.host=101.010.101.01
spring.data.mongodb.port=10011
spring.data.mongodb.repositories.enabled=true
server.context-path=/so
server.port=10012
spring.jackson.time-zone=Asia/Seoul


```

<br>
<br>
