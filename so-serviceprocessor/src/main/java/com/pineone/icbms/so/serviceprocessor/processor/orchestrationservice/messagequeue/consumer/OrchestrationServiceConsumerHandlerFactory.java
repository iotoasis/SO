package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.messagequeue.consumer;

/**
 * SpringKafkaOrchestrationServiceConsumerHandler factory.<BR/>
 *
 * Created by uni4love on 2017. 1. 4..
 */
public class OrchestrationServiceConsumerHandlerFactory {
    /**
     * max count
     */
    private int MAX_COUNT = 5;

    /**
     * return SpringKafkaOrchestrationServiceConsumerHandler instance.<BR/>
     *
     * @return SpringKafkaOrchestrationServiceConsumerHandler
     */
    public static OrchestrationServiceConsumerHandler getOrchestrationServiceConsumerHandler(int id) {
        return new OrchestrationServiceConsumerHandler(id);
    }
}
