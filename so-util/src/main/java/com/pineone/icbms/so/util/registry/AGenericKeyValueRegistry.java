package com.pineone.icbms.so.util.registry;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Key-value registry abstract generic class.<BR/>
 *
 * Created by uni4love on 2016. 11. 16..
 */
abstract public class AGenericKeyValueRegistry<K, V> implements IGenericKeyValueRegistry<K, V>, Serializable {
    /**
     * key-value store
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Map<K, V> store = null;

    /**
     * registry name
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String name = null;

    /**
     * logger
     */
    transient protected Logger log = getLogger();

    /**
     * constructor
     */
    public AGenericKeyValueRegistry() {
        store = createStore();
    }

    public AGenericKeyValueRegistry(String name) {
        this();
        this.name = name;
    }

    /**
     * add a key-value set.<BR/>
     *
     * @param value value
     */
    @Override
    public V addValue(K key, V value) {
        V result = null;
        if (store.containsKey(key)) {
            log.warn(
                    "The key exists already in registry: {}, this item is skipped.",
                    key);
        } else {
            result = store.put(key, value);
            log.info("The value registered to registry by key {}", key);
        }
        return result;
    }

    /**
     * remove a value<BR/>
     *
     * @param key key(ex: class name with full package name)
     * @return removed value
     */
    @Override
    public synchronized V removeValue(K key) {
        if (!store.containsKey(key)) {
            log.warn("The value NOT exists in registry: {}", key);
        }
        return store.remove(key);
    }

    /**
     * return value by key<BR/>
     *
     * @param key key(ex: class name with full package name)
     * @return value
     */
    @Override
    public V getValue(K key) {
        if (!store.containsKey(key)) {
            log.warn("The value NOT exists: {}", key);
        }
        return store.get(key);
    }

    /**
     * return key set.<BR/>
     *
     * @return key set
     */
    @Override
    public Set<K> keySet() {
        return store.keySet();
    }

    public Map getStore() {
        return store;
    }

    public void setStore(Map<K, V> store) {
        this.store = store;
    }

    /**
     * registry name
     *
     * @return registry name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("store: " + store);
        return sb.toString();
    }

    /**
     * get logger instance.<BR/>
     */
    private Logger getLogger() {
        if (log == null) {
            log = LoggerFactory.getLogger(getClass());
        }
        return log;
    }

    /**
     * return interface for returning Store<BR/>
     *
     * @return Map
     */
    abstract protected Map<K, V> createStore();
}
