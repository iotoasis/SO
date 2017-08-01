#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)

echo .

${SCRIPT_ABS_PATH}/stop-kafka.sh

sleep 4

${SCRIPT_ABS_PATH}/stop-zookeeper.sh