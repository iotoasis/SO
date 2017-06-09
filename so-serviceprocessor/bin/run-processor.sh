#!/bin/sh
MODE="product"
JAR_PATH=../build/libs/so-serviceprocessor-1.0.0-SNAPSHOT.jar
CONFIG_PATH="file:../conf/application-${MODE}.properties"

function usage() {
    echo usage\) run-processor.sh -P[processor1,processor2,..]
    return
}

function run() {
    CMD="java -jar ${JAR_PATH} --spring.config.location=${CONFIG_PATH} $1"
    echo ${CMD}
    ${CMD}
    return
}

if [ 1 -eq $# ]; then
    run $1
else
    usage
fi
