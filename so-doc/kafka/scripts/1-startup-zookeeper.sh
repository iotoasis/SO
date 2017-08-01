#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

ZOOKEEPER_CONFIG_FILE_PATH=${KAFKA_CONFIG_PATH}/zookeeper.properties
echo -ZOOKEEPER CONFIG FILE PATH=${ZOOKEEPER_CONFIG_FILE_PATH}

echo
echo running zookeeper...

#${KAFKA_BIN_PATH}/zookeeper-server-start.sh ${ZOOKEEPER_CONFIG_FILE_PATH} > /dev/null 2>&1 &
${KAFKA_BIN_PATH}/zookeeper-server-start.sh ${ZOOKEEPER_CONFIG_FILE_PATH}
