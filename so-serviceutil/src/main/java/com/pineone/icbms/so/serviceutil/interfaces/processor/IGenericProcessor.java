package com.pineone.icbms.so.serviceutil.interfaces.processor;

/**
 * serviceprocessor generic interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
public interface IGenericProcessor extends IProcessor {
    /**
     * called method before process()
     */
    void beforeProcess();

    /**
     * called method after process()
     */
    void afterProcess();
}
