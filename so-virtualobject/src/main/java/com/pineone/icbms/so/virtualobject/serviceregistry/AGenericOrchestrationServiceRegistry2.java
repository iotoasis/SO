package com.pineone.icbms.so.virtualobject.serviceregistry;

import com.pineone.icbms.so.virtualobject.orchestrationservice.IOrchestrationService;
import com.pineone.icbms.so.util.registry.AGenericKeyValueRegistry;

import java.util.Map;

/**
 * Abstract Generic orchestrationservice registry<BR/>
 * Created by uni4love on 2016. 11. 17..
 */
abstract public class AGenericOrchestrationServiceRegistry2<K>
        extends AGenericKeyValueRegistry<K, IOrchestrationService>
        implements IOrchestrationServiceRegistry<K> {

    /**
     * constructor
     */
    public AGenericOrchestrationServiceRegistry2() {
        store = createStore();
    }

    /**
     * register a orchestrationservice<BR/>
     *
     * @param service orchestrationservice
     */
    public synchronized void registerOrchestrationService(IOrchestrationService service) {
        registerOrchestrationService((K)service.getName(), service);
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
            log.warn("The orchestrationservice NOT exists in orchestrationservice registry: {}",
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
     * @return IOrchestrationService Map
     */
    abstract public Map<K, IOrchestrationService> createStore();
}
