#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

echo .

${KAFKA_BIN_PATH}/kafka-topics.sh --describe --zookeeper localhost:2181

