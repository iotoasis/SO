#!/bin/sh

sh ./run-processor.sh -Pcom.pineone.icbms.so.processor.devicecontrol.DeviceControlProcessor > /dev/null 2>&1 &
sleep 3
sh ./run-processor.sh -Pcom.pineone.icbms.so.processor.virtualobject.VirtualObjectProcessor > /dev/null 2>&1 &
sleep 3
sh ./run-processor.sh -Pcom.pineone.icbms.so.processor.orchestrationservice.OrchestrationServiceProcessor > /dev/null 2>&1 &
sleep 3
sh ./run-processor.sh -Pcom.pineone.icbms.so.processor.context.ContextModelProcessor > /dev/null 2>&1 &
