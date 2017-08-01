#!/usr/bin/env bash
bin/kafka-topics.sh --create --topic consumer-tutorial --replication-factor 1 --partitions 3 --zookeeper localhost:2181
