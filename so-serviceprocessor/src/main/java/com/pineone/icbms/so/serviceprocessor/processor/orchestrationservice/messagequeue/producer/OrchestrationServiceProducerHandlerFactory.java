package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.messagequeue.producer;

/**
 * SpringKafkaOrchestrationServiceProducerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class OrchestrationServiceProducerHandlerFactory {
    /**
     * return SpringKafkaOrchestrationServiceProducerHandler instance.<BR/>
     *
     * @return SpringKafkaOrchestrationServiceProducerHandler
     */
    public static OrchestrationServiceProducerHandler getOrchestrationServiceProducerHandler(int id) {
        return new OrchestrationServiceProducerHandler(id);
    }
}
