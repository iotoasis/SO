#!/bin/sh
RUN_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
KAFKA_PATH=${RUN_ABS_PATH%/*}/kafka
KAFKA_BIN_PATH=${KAFKA_PATH}/bin
KAFKA_CONFIG_PATH=${KAFKA_PATH}/config

echo
echo -CURRENT PATH=${PWD}
echo -RUN PATH=${RUN_ABS_PATH}
echo -KAFKA PATH=${KAFKA_PATH}
echo -KAFKA BIN PATH=${KAFKA_BIN_PATH}

