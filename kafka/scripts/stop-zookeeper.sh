#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

echo .

echo stopping zookeeper...
${KAFKA_BIN_PATH}/zookeeper-server-stop.sh
