package com.pineone.icbms.so.resources.context;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * generic context abstract class.<BR/>
 * Created by uni4love on 2015. 05. 30..
 */
abstract public class AGenericContext<V> implements IGenericContext<String, V>
{
    /**
	 * logger
	 */
	private Logger log = LoggerFactory.getLogger(AGenericContext.class);

    /**
     * key-value store
     */
    protected Map<String, V> store = null;

	/**
	 * constructor
	 */
	public AGenericContext()
	{
		store = createStore();
	}

	/**
	 * add a service<BR/>
	 *
	 * @param value
	 *            value
	 */
	@Override
	public void addValue(String key, V value)
	{
		if (store.containsKey(key))
		{
			log.warn(
					"The key exists already in registry: {}, this item is skipped.",
					key);
		}
		else
		{
			store.put(key, value);
			log.info("The value registered to registry: {}", key);
		}
	}

	/**
	 * remove a value<BR/>
	 *
	 * @param key
	 *            key(ex: class name with full package name)
	 * @return removed value
	 */
	@Override
	public synchronized V removeValue(String key)
	{
		if (!store.containsKey(key))
		{
			log.warn("The value NOT exists already in registry: {}", key);
		}
		return store.remove(key);
	}

	/**
	 * return value by key<BR/>
	 *
	 * @param key
	 *            key(ex: class name with full package name)
	 * @return value
	 */
	@Override
	public V getValue(String key)
	{
		if (!store.containsKey(key))
		{
			log.warn("The value NOT exists: {}", key);
		}
		return store.get(key);
	}

	/**
	 * return interface for returning Store<BR/>
	 * 
	 * @return Map
	 */
	abstract protected Map<String, V> createStore();

}
