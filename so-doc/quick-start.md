# Quick Start

Oasis SO FrameWork를 처음 접하는 분들이 소스를 다운받고 쉽게 시험할 수 있도록 안내합니다.

SO Server 시험은 아래의 순서로 진행할 수 있습니다.

> 1. MariaDB, JDK 다운로드 및 설치
> 2. Oasis SO FrameWork 소스 다운로드
> 3. MariaDB 기본 셋팅
> 4. SO Server 빌드
> 5. SO Server 설정 및 실행
> 6. HTTP 애뮬레이터(PostMan)를 이용한 시험

## Requirements
* JDK 7+ 
* Mariadb 
* Windows / Linux  

## 따라하기

#### (1) MariaDB, JDK 다운로드 및 설치
 - [mariaDB 설치](https://mariadb.org/download/)
 - [JDK 설치](http://docs.oracle.com/javase/7/docs/webnotes/install/)

#### (2) Oasis SO Server 소스 다운로드
 - [릴리즈 페이지](https://github.com/iotoasis/SO/releases)에서 SO 소스 및 설치관련 파일을 다운받는다.

#### (3) Kafka / Zookeeper 설치 - 실행 
- [Kafka 설치](https://kafka.apache.org)
	- 또는 console 입력  
	
	```				
	# curl -LOv http://apache.mirror.cdnetworks.com/kafka/0.10.1.0/kafka_2.11-0.10.1.0.tgz
	``` 
	
	- 압축 해제 및 링크 :

	``` 		
	# tar xvfz kafka_2.11-0.10.1.0.tgz
	# ln -s kafka_2.11-0.10.1.0 kafka
	# cd kafka
	```
	
- 릴리즈페이지에서 받은 소스중 so-doc 폴더 내부의 scripts 다운받은 kafka 폴더와 동일하게 위치시킨다.
![카프카 폴더 위치](https://github.com/iotoasis/SO/blob/master/so-doc/img/kafka_file_pwd.png)

   - console 1을 실행하여 입력 (zookeeper 실행)
   ```
   ./scripts/1-startup-zookeeper.sh
   ```
   
   - console 2를 실행하여 입력 (kafka 실행)
   ```
   ./scripts/2-startup-kafka.sh
   ```
   
   - console 3을 실행하여 입력 (SO에서 사용하는 kafka topic 자동생성)
   ```
   ./scripts/create-topics-so.sh
   ```

   - SO를 실행하는동안 console 1.2 는 실행중이여야 한다. (Queue로 사용)

- 참고 : [kafka 실행 테스트](https://github.com/iotoasis/SO/blob/master/so-doc/kafka_test.md)

#### (4) MariaDB 기본 셋팅
 - [릴리즈 페이지](https://github.com/iotoasis/SO/releases)에서 다운받은 SO 소스중 so-doc 폴더 내부의 db_script 스크립트파일(sb_script.txt)를 실행시켜서 기본 컬렉션 및 색인을 생성한다.
 - so-web/conf/applicaiont-product.yml 안에 자신의 DB 접속 정보를 등록한다. 
```
spring.datasource.url=jdbc:mysql://localhost:3306/so?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=user(변경)
spring.datasource.password=pw(변경) 
spring.datasource.driverClassName=com.mysql.jdbc.Driver
```

#### (5) SO Server 빌드
 - [릴리즈 페이지](https://github.com/iotoasis/SO/releases)에서 다운받은 SO 소스를 이클립스에서 불러와서 Build한다.
 - SO 소스를 빌드하는 방법은 [소스 Build방법](https://github.com/iotoasis/SO/blob/master/so-doc/build_eclipse.md)페이지를 참고한다.

#### (6) SO Server 설정 및 실행
 - 다운 받은 소스의 so-web/conf/application.properties 파일을 오픈하여 SO 설정을 수정한다.
 - SO Server 설정방법은 [SO 서버 설정방법](https://github.com/iotoasis/SO/blob/master/so-doc/configuration.md)페이지를 참고한다.

#### (7) HTTP 애뮬레이터(PostMan)을 이용한 시험
 - PostMan 프로그램을 설치한다. [PostMan 다운로드](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop)
 - PostMan에서 URL의 "localhost" 부분을 SO 서버를 실행한 서버의 IP로 수정하여 메시지 전송을 시험한다. [Test 페이지](https://github.com/iotoasis/SO/blob/master/so-doc/so-test.md)를 참조한다.

<br>
<br>
