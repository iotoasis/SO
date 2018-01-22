#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

echo .

${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic contextmodel
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic orchestrationservice
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic compositevirtualobject
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic virtualobject
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic devicecontrol
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic devicecontrol-seq
${KAFKA_BIN_PATH}/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic traking
