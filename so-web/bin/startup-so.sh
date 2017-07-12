#!/bin/sh
#profile list: product or dev
SO_HOME=/svc/apps/so
SO_SERVICE_HOME=${SO_HOME}/so
ACTIVE_MODE="dev"
PROFILES="${ACTIVE_MODE}"
#JAR_PATH=../libs/so-1.0.0-SNAPSHOT.jar
JAR_PATH=${SO_SERVICE_HOME}/libs/so.jar
CONFIG_PATH="file:${SO_SERVICE_HOME}/conf/application-${ACTIVE_MODE}.properties"

CMD_SPRING_PROFILES="--spring.profiles.active=${PROFILES}"
CMD_SPRING_CONFIG="--spring.config.location=${CONFIG_PATH}"
CMD_SO_SERVICE_HOME="--SO_SERVICE_HOME=${SO_SERVICE_HOME}"
CMD_OPTIONS="-jar ${JAR_PATH} ${CMD_SPRING_CONFIG} ${CMD_SPRING_PROFILES} ${CMD_SO_SERVICE_HOME} $1"
CMD="java ${CMD_OPTIONS}"
    
function print_cmd() {
	echo -Environments:
	echo +Active Mode: ${ACTIVE_MODE}
	echo +Profiles: ${PROFILES}
	echo +Path:SO service home: ${SO_SERVICE_HOME}
	echo +Path:binary: ${JAR_PATH}
	echo +Path:config: ${CONFIG_PATH}
    echo +Command: ${CMD}
    echo .
    return
}

function run() {
	
    echo .
    ${CMD}
    return
}

print_cmd 
run
