package com.pineone.icbms.so.virtualobject.serviceregistry;

import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Generic orchestrationservice registry<BR/>
 * Created by uni4love on 2016. 11. 16..
 */
public abstract class AGenericOrchestrationServiceRegistry<K> implements IOrchestrationServiceRegistry<K> {
    /**
     * services store
     */
    protected Map<K, IOrchestrationService> store = null;

    /**
     * logger
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * constructor
     */
    public AGenericOrchestrationServiceRegistry() {
        store = createStore();
    }

    /**
     * register a orchestrationservice<BR/>
     *
     * @param service orchestrationservice
     */
    public synchronized void registerOrchestrationService(IOrchestrationService service) {
        registerOrchestrationService((K) service.getName(), service);
    }

    /**
     * register a orchestrationservice<BR/>
     *
     * @param service orchestrationservice
     */
    @Override
    public synchronized void registerOrchestrationService(K key, IOrchestrationService service) {
        if (store.containsKey(key)) {
            log.warn(
                    "The orchestrationservice key exists already in orchestrationservice registry: {}, this item is skipped.",
                    service.getName());
        } else {
            store.put(key, service);
            log.info("The orchestrationservice registered to orchestrationservice registry: {}", key);
        }
    }

    /**
     * unregister a orchestrationservice<BR/>
     *
     * @param key orchestrationservice key(ex: class name with full package name)
     * @return unregistered orchestrationservice
     */
    @Override
    public synchronized IOrchestrationService unregisterOrchestrationService(K key) {
        if (!store.containsKey(key)) {
            log.warn("The orchestrationservice NOT exists already in orchestrationservice registry: {}",
                    key);
        }
        return store.remove(key);
    }

    /**
     * return orchestrationservice by orchestrationservice key<BR/>
     *
     * @param key orchestrationservice key(ex: class name with full package name)
     * @return orchestrationservice
     */
    @Override
    public IOrchestrationService getOrchestrationService(K key) {
        if (!store.containsKey(key)) {
            log.warn("The orchestrationservice NOT exists: {}", key);
        }
        return store.get(key);
    }

    /**
     * return interface for returning Store<BR/>
     *
     * @return Map
     */
    abstract protected Map<K, IOrchestrationService> createStore();
}
