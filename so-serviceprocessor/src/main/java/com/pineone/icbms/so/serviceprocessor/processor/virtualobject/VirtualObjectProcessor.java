package com.pineone.icbms.so.serviceprocessor.processor.virtualobject;

import com.pineone.icbms.so.util.Settings;
import com.pineone.icbms.so.serviceprocessor.processor.virtualobject.messagequeue.consumer.VirtualObjectConsumerHandler;
import com.pineone.icbms.so.serviceutil.interfaces.processor.AGenericProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * VirtualObjectConsumerHandler runner.<BR/>
 *
 * Created by uni4love on 2017. 1. 12..
 */
public class VirtualObjectProcessor extends AGenericProcessor {
    /**
     * constructor
     */
    public VirtualObjectProcessor() {

    }

    /**
     * constructor
     *
     * @param id id
     * @param name name
     */
    public VirtualObjectProcessor(String id, String name) {
        super(id, name);
    }

    /**
     * begin Handler on thread.
     */
    @Override
    public void process() {
        ExecutorService executorService = Executors.newFixedThreadPool(Settings.HANDLER_COUNT);
        List<VirtualObjectConsumerHandler> consumerList = new ArrayList<>();
        for (int i = 0; i < Settings.HANDLER_COUNT; i++) {
            VirtualObjectConsumerHandler consumer = new VirtualObjectConsumerHandler(i);
            consumerList.add(consumer);
            executorService.submit(consumer);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (VirtualObjectConsumerHandler consumer : consumerList) {
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
