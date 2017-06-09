package com.pineone.icbms.so.virtualobject.serviceregistry;

import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;
import com.pineone.icbms.so.util.registry.IRegistry;

/**
 * ServiceRegistry interface<BR/>
 * Created by uni4love on 2016. 11. 16..
 */
public interface IOrchestrationServiceRegistry<K> extends IRegistry {
    /**
     * register orchestrationservice<BR/>
     *
     * @param key     key for orchestrationservice
     * @param service IOrchestrationService interface
     */
    void registerOrchestrationService(K key, IOrchestrationService service);

    /**
     * unregister orchestrationservice<BR/>
     *
     * @param key orchestrationservice class name(ex: class name with full package name)
     * @return unregistered orchestrationservice
     */
    IOrchestrationService unregisterOrchestrationService(K key);

    /**
     * get orchestrationservice<BR/>
     *
     * @param key key for orchestrationservice
     * @return IOrchestrationService
     */
    IOrchestrationService getOrchestrationService(K key);
}
