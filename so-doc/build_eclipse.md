# SODA Framework Server Build

## 이클립스 사용 빌드 방법

다운받은 SODA Framework 서버 소스를 Eclipse를 이용해서 Build하는 방법을 설명합니다.

Build를 위한 요구조건은 아래와 같습니다.

## Requirements
* JDK 7+
* Windows / Linux
* eclipse/sts (https://spring.io/tools/sts/all)
* gradle

Build 실행 순서는 아래와 같습니다.

> 1. 이클립스로 소스 import
> 2. Gradle을 이용해서 소스 컴파일
> 3. 설정 파일 추가 및 수정
> 4. SODA Framework 서버 실행
> 5. PostMan을 활용한 SODA Framework 서버 시험


#### (1) 이클립스로 소스 import
- 메뉴에서 File/Import 메뉴를 선택하여 Import 창을 열어  Gradle(STS)/Gradle(STS) Project를 선택한 후 소스가 저장된 폴더를 선택합니다.
- Import Gradle Project 화면에서 오른쪽 Build Model 버튼을 클릭하여 프로젝트의 dependency및 lib를 받는다.
- Import된 프로젝트는 아래와 같이 표시됩니다.


![Source Import1](https://github.com/iotoasis/SO/blob/master/so-doc/img/eclipse_import1.png)

![Source Import2](https://github.com/iotoasis/SO/blob/master/so-doc/img/eclipse_import2.png)

![Source Import3](https://github.com/iotoasis/SO/blob/master/so-doc/img/eclipse_import3.png)


#### (2) 설정 파일 추가 및 수정
- Release 페이지에서 다운받은 설정파일(so-web/conf/application-product.yml)을 수정 합니다. (기본 설정 되어있음)
- DB설정 등 로컬환경에 맞게 설정파일을 수정합니다. 설정파일에 대한 자세한 내용은 [SO 서버설정](https://github.com/iotoasis/SO/blob/master/so-doc/configuration.md)페이지를 참고하세요.


#### (3) Gradle을 이용한 Build
- Ctrl+Alt+Shift+R 로 Gradle Build 실행.
- Project를 so-web 선택. Tasks에 build 명령어 입력.

![build gradle](https://github.com/iotoasis/SO/blob/master/so-doc/img/eclipse_build.png)

#### (4) 빌드된 so.jar 파일 (so-web/build/lib/so.jar)를 실행시킨다.
- so-web/build/libs 폴더로 이동
- 명령어 실행 : java -jar so.jar --spring.config.location=../../conf/application-product.yml

#### (5) PostMan을 활용한 SO 서버 시험
- POSTMAN을 활용하여 SO 서버를 시험합니다.
- POSTMAN 시험을 위한 스크립트 파일은 release 페이지에서 다운받을 수 있습니다.
- POSTMAN을 활용한 SO 서버 시험에 관한 자세한 내용은 [Test 페이지](https://github.com/iotoasis/SO/blob/master/so-doc/so-test.md)에서 확인할 수 있습니다.


