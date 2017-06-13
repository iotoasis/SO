## SODA Framework Server TEST

다운 받은 SODA Framework 서버를 테스트 하기 위한 방법을 설명 합니다.

TEST 실행 순서는 아래와 같습니다.

> 1. [SO 서버 설정](./configuration.md)을 한다.
> 2. mongodb script를 이용하여 TEST DB 생성 한다.
> 3. SO Servce 실행 한다.
> 4. POSTMAN으로 생성한 SO(Service Orchestration)를 실행한다.


<br>

#### (1) SO 서버 설정
- [SO 서버 설정](./configuration.md)페이지에서 설정한다.

- ex)
~~~
spring.data.mongodb.database=sotest
spring.data.mongodb.host=127.0.0.1
spring.data.mongodb.port=27017
spring.data.mongodb.repositories.enabled=true
server.context-path=/so
server.port=10080
spring.jackson.time-zone=Asia/Seoul
~~~
<br>

#### (2) mongodb 생성
- Release 페이지에서 다운 받은 소스에서 [MongoDB Script](./mongodb_script.txt)를 복사 하여 MongoDB Shell에 실행 시킨다.
- 생성된 DB를 확인 한다.
<br>

#### (3) SO Server 실행
- target 폴더(so-web/build/libs)에서 아래 명령으로 서버를 실행합니다.

 ```
java -jar service-orchestration-2.0.0-SANPSHOT.jar
 ```

<br>
#### (4) POSTMAN으로 생성한 SO(Service Orchestration)를 실행
- Headers에 **Content-Type**는 **application/json**으로 설정
- POST에 **./so/servicemodel/control** 입력
- Body에 **{ "id": "sm-sotest" }** 입력
- Send버튼 클릭
- SO Server가 실행 되는 것을 확인 한다.

![POSTMAN HEADERS](./img/postman1.png)
<br>
![POSTMAN BODY](./img/postman2.png)