package com.pineone.icbms.so.serviceutil.interfaces.registry;

import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;

/**
 * serviceprocessor registry generic interface.<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
public interface IGenericProcessorRegistry<K, V> extends IProcessorRegistry<K, V> {
    /**
     * add a serviceprocessor.<BR/>
     *
     * @param processor
     */
    public void addProcessor(IGenericProcessor processor);

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processor serviceprocessor
     * @return true/false
     */
    public boolean existProcessor(IGenericProcessor processor);
}
