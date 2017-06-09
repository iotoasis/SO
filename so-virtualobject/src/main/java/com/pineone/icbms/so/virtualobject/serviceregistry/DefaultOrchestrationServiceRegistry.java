package com.pineone.icbms.so.virtualobject.serviceregistry;

import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServiceRegistry default class.<BR/>
 * Created by uni4love on 2016. 11. 16..
 */
public class DefaultOrchestrationServiceRegistry<K> extends AGenericOrchestrationServiceRegistry {
    /**
     * registry name
     */
    private static String name = "DEFAULT ORCHESTRATION SERVICE REGISTRY";

    /**
     * constructor
     */
    public DefaultOrchestrationServiceRegistry() {
    }

    /**
     * return ServiceRegistry singleton instance<BR/>
     *
     * @return ServiceRegistry singleton instance
     */
    public static final DefaultOrchestrationServiceRegistry getInstance() {
        return SingletonHolder.singleton;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected Map createStore() {
        return new ConcurrentHashMap<K, IOrchestrationService>();
    }

    /**
     * inner singleton instance class
     */
    private static final class SingletonHolder {
        static final DefaultOrchestrationServiceRegistry singleton = new DefaultOrchestrationServiceRegistry();
    }
}
