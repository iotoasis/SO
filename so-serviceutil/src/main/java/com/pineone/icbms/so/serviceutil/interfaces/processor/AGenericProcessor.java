package com.pineone.icbms.so.serviceutil.interfaces.processor;

import com.pineone.icbms.so.virtualobject.common.AGenericIdentity;

/**
 * serviceprocessor abstract generic class.<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
abstract public class AGenericProcessor extends AGenericIdentity implements IGenericProcessor, Runnable {
    /**
     * constructor
     */
    public AGenericProcessor() {
    }

    /**
     * constructor<BR/>
     *
     * @param id id
     */
    public AGenericProcessor(String id) {
        super(id);
    }

    /**
     * constructor
     *
     * @param id   id
     * @param name name
     */
    public AGenericProcessor(String id, String name) {
        super(id, name);
    }

    @Override
    public void run() {
        process();
    }

    /**
     * called method before process()
     */
    @Override
    public void beforeProcess() {
        //TODO: tracking entity
        //..
        before();
    }

    /**
     * called method after process()
     */
    @Override
    public void afterProcess() {
        //TODO: tracking entity
        //..
        after();
    }

    /**
     * called by beforeProcess().<BR/>
     */
    abstract public void before();

    /**
     * called by afterProcess().<BR/>
     */
    abstract public void after();
}
