package com.pineone.icbms.so.resources.processor;

import com.pineone.icbms.so.resources.service.IService;

/**
 * Created by existmaster on 2015. 12. 23..
 */
public interface IServiceCreator {

    /**
     * This method return IService type object.
     *
     * @param serviceModelID key the serviceModel to be searched for.
     * @return the searched IService Object.
     */
    IService createServiceByID(String serviceModelID);
}
