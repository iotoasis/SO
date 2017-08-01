#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

KAFKA_CONFIG_FILE_PATH=${KAFKA_CONFIG_PATH}/server.properties
echo -KAFKA CONFIG FILE PATH=${KAFKA_CONFIG_FILE_PATH}

echo
echo running kafka...

#${KAFKA_BIN_PATH}/kafka-server-start.sh ${KAFKA_CONFIG_FILE_PATH} > /dev/null 2>&1 &
${KAFKA_BIN_PATH}/kafka-server-start.sh ${KAFKA_CONFIG_FILE_PATH}
