package com.pineone.icbms.so.serviceprocessor.route;

import com.pineone.icbms.so.serviceutil.interfaces.processor.IGenericProcessor;
import com.pineone.icbms.so.serviceprocessor.registry.SequenceProcessorRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Process Line class.<BR/>
 *
 * This class support for serviceprocessor, filter, rule process.<BR/>
 *
 * Created by uni4love on 2016. 11. 24..
 */
public class ProcessLine {
    /**
     * logger
     */
    private Logger log = LoggerFactory.getLogger(ProcessLine.class.getClass());

    /**
     * process registry
     */
    private SequenceProcessorRegistry processRegistry = new SequenceProcessorRegistry();

    /**
     * add a serviceprocessor.<BR/>
     *
     * @param processor
     */
    public void addProcessor(IGenericProcessor processor) {
        this.processRegistry.addProcessor(processor);
    }

    /**
     * return a serviceprocessor.<BR/>
     *
     * @param key key for a serviceprocessor
     * @return serviceprocessor
     */
    public IGenericProcessor getProcessor(String key) {
        return this.processRegistry.getValue(key);
    }

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processor serviceprocessor
     * @return true/false
     */
    public boolean existProcessor(IGenericProcessor processor) {
        return this.processRegistry.existProcessor(processor.getId());
    }

    /**
     * return existence of serviceprocessor<BR/>
     *
     * @param processorName serviceprocessor name
     * @return true/false
     */
    public boolean existProcessor(String processorName) {
        return this.processRegistry.existProcessor(processorName);
    }
}
