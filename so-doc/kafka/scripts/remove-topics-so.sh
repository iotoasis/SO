#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

echo .

${KAFKA_BIN_PATH}/kafka-topics.sh --delete --zookeeper localhost:2181 --topic contextmodel
${KAFKA_BIN_PATH}/kafka-topics.sh --delete --zookeeper localhost:2181 --topic orchestrationservice
${KAFKA_BIN_PATH}/kafka-topics.sh --delete --zookeeper localhost:2181 --topic virtualobject
${KAFKA_BIN_PATH}/kafka-topics.sh --delete --zookeeper localhost:2181 --topic devicecontrol
${KAFKA_BIN_PATH}/kafka-topics.sh --delete --zookeeper localhost:2181 --topic devicecontrol-seq
