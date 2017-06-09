#!/bin/sh
ACTIVE_MODE="-Dspring.profiles.active=product"
JAR_PATH=../build/libs/so-web-1.0.0-SNAPSHOT.jar
COMMAND_OPTIONS="$1 ${ACTIVE_MODE}"
CONFIG_PATH="file:../conf/application-product.properties"

function run() {
    CMD="java ${COMMAND_OPTIONS} -jar ${JAR_PATH}  --springkafka.config.location=${CONFIG_PATH}"
    echo ${CMD}
    ${CMD}
    return
}

run
