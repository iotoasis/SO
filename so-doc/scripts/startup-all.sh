#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)

echo .

${KAFKA_PATH}/scripts/1-startup-zookeeper.sh

sleep 2

echo .
${KAFKA_PATH}/scripts/2-startup-kafka.sh
