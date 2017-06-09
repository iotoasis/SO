package com.pineone.icbms.so.serviceprocessor.registry;

import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;
import com.pineone.icbms.so.serviceutil.interfaces.registry.IGenericProcessorRegistry;
import com.pineone.icbms.so.util.registry.AGenericKeyValueRegistry;

/**
 * Abstract Generic serviceprocessor registry<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
abstract public class AGenericProcessRegistry
        extends AGenericKeyValueRegistry<String, IGenericProcessor>
        implements IGenericProcessorRegistry<String, IGenericProcessor> {

     /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processorName serviceprocessor name
     * @return true/false
     */
    abstract public boolean existProcessor(String processorName);
}
