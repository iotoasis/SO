package com.pineone.icbms.so.serviceprocessor.filter;

import com.pineone.icbms.so.serviceutil.interfaces.filter.IFilter;
import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;

/**
 * Message Expire date for message queue filter class.<BR/>
 *
 * Created by uni4love on 2017. 1. 15..
 */
public class MessageExpireDateFilter<V> implements IFilter<V>, IGenericProcessor {
    @Override
    public V filtering(V v) {
        return null;
    }

    /**
     * return id.<BR/>
     *
     * @return id
     */
    @Override
    public String getId() {
        return null;
    }

    /**
     * return name.<BR/>
     *
     * @return name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * called method before process()
     */
    @Override
    public void beforeProcess() {

    }

    /**
     * called method after process()
     */
    @Override
    public void afterProcess() {

    }

    /**
     * process method.
     */
    @Override
    public void process() {
        //implements..
        //got from message queue, and filter
        //call filtering() method.
    }
}
