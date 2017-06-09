package com.pineone.icbms.so.virtualobject.serviceregistry;

import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServiceRegistry default class.<BR/>
 * Created by uni4love on 2016. 11. 17..
 */
public class DefaultOrchestrationServiceRegistry2<K extends String> extends AGenericOrchestrationServiceRegistry2<K> {
    /**
     * registry name
     */
    private static String name = "DEFAULT ORCHESTRATION SERVICE REGISTRY 2";

    /**
     * constructor
     */
    public DefaultOrchestrationServiceRegistry2() {
    }

    /**
     * return ServiceRegistry singleton instance<BR/>
     *
     * @return ServiceRegistry singleton instance
     */
    public static final DefaultOrchestrationServiceRegistry2 getInstance() {
        return SingletonHolder.singleton;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map createStore() {
        return new ConcurrentHashMap<K, IOrchestrationService>();
    }

    /**
     * inner singleton instance class
     */
    private static final class SingletonHolder {
        static final DefaultOrchestrationServiceRegistry2 singleton = new DefaultOrchestrationServiceRegistry2();
    }
}
