## SODA Framework Server TEST

다운 받은 SODA Framework 서버를 테스트 하기 위한 방법을 설명 합니다.

TEST 실행 순서는 아래와 같습니다.

> 1. [SO 서버 설정](https://github.com/iotoasis/SO/blob/master/so-doc/configuration.md)을 한다.
> 2. mariaDB script를 이용하여 TEST DB 생성 한다.
> 3. SO Servce 실행 한다.
> 4. POSTMAN으로 생성한 SO(Service Orchestration)를 실행한다.



#### (1) SO 서버 설정
- [SO 서버 설정](https://github.com/iotoasis/SO/blob/master/so-doc/configuration.md)페이지에서 설정한다.

- ex)
~~~
server: 
	port: 10080
	contextPath: /so
spring: 
  datasource: 
    url: jdbc:mysql://localhost:3306/so?useUnicode=true&characterEncoding=utf-8
    username: usr
    password: pwd
    driverClassName: com.mysql.jdbc.Driver
  jackson: 
    time-zone: Asia/Seoul
~~~


#### (2) mariaDB 생성
- Release 페이지에서 다운 받은 소스에서 [MongoDB Script](https://github.com/iotoasis/SO/blob/master/so-doc/db_script.txt)를 복사 하여 MongoDB Shell에 실행 시킨다.
- 생성된 DB를 확인 한다.


#### (3) SO Server 실행
- target 폴더(so-web/build/libs)에서 아래 명령으로 서버를 실행합니다.

 ```
java -jar so.jar --spring.config.location=../../conf/application-product.yml
 ```

#### (4) POSTMAN으로 생성한 SO(Service Orchestration)를 실행
- Headers에 **Content-Type**는 **application/json**으로 설정
- POST에 **http://localhost:8080/so/service/context/cm/simulate** 입력
- Body
```
{
  "simulatorType": "web",
  "cmd": "query",
  "contextId": "cm-fire-test",
  "contents": [
    {
      "loc": "http://www.iotoasis.org/ontology/test"
    }
  ]
}
```
- Send버튼 클릭
- SO Server가 실행 되는 것을 확인 한다.

![POSTMAN HEADERS](https://github.com/iotoasis/SO/blob/master/so-doc/img/postman-test.png)
<br>

