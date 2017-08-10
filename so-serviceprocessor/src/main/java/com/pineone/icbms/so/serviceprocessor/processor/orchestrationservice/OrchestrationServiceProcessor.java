package com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice;

import com.pineone.icbms.so.serviceprocessor.processor.orchestrationservice.messagequeue.consumer.OrchestrationServiceConsumerHandler;
import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.serviceutil.interfaces.processor.AGenericProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * OrchestrationServiceConsumerHandler runner.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public class OrchestrationServiceProcessor extends AGenericProcessor {
    /**
     * constructor
     */
    public OrchestrationServiceProcessor() {

    }

    /**
     * constructor
     *
     * @param id id
     * @param name name
     */
    public OrchestrationServiceProcessor(String id, String name) {
        super(id, name);
    }

    /**
     * begin Handler on thread.
     */
    @Override
    public void process() {
        ExecutorService executorService = Executors.newFixedThreadPool(Settings.HANDLER_COUNT);
        List<OrchestrationServiceConsumerHandler> consumerList = new ArrayList<>();
        for (int i = 0; i < Settings.HANDLER_COUNT; i++) {
            OrchestrationServiceConsumerHandler consumer = new OrchestrationServiceConsumerHandler(i);
            consumerList.add(consumer);
            executorService.submit(consumer);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (OrchestrationServiceConsumerHandler consumer : consumerList) {
                    consumer.shutdown();
                }
                executorService.shutdown();
                try {
                    executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * called method before process()
     */
    @Override
    public void before() {

    }

    /**
     * called method after process()
     */
    @Override
    public void after() {

    }
}
