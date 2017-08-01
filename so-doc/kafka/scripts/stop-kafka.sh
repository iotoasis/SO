#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/common-env.sh

echo .

echo stopping kafka...
${KAFKA_BIN_PATH}/kafka-server-stop.sh
