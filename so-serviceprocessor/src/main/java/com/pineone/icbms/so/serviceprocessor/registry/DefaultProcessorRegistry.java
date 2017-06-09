package com.pineone.icbms.so.serviceprocessor.registry;

import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * serviceprocessor default class.<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
public class DefaultProcessorRegistry extends AGenericProcessRegistry {
    /**
     * return interface for returning Store<BR/>
     *
     * @return Map
     */
    @Override
    protected Map<String, IGenericProcessor> createStore() {
        return new HashMap<String, IGenericProcessor>();
    }

    /**
     * add a serviceprocessor.<BR/>
     *
     * @param processor
     */
    @Override
    public void addProcessor(IGenericProcessor processor) {
       addValue(processor.getId(), processor);
    }

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processor serviceprocessor
     * @return true/false
     */
    @Override
    public boolean existProcessor(IGenericProcessor processor) {
        return this.store.containsKey(processor.getId());
    }

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processorName serviceprocessor name
     * @return true/false
     */
    @Override
    public boolean existProcessor(String processorName) {
        return this.store.containsKey(processorName);
    }
}
