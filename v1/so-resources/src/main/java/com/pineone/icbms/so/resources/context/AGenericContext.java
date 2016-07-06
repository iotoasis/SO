package com.pineone.icbms.so.resources.context;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic context abstract class.<BR/>
 * Created by uni4love on 2015. 05. 30..
 */
abstract public class AGenericContext<V> extends AGenericVirtualObject
		implements IGenericContext<String, V>, Serializable
{
	private static final long serialVersionUID = -7915130755854696838l;

	/**
	 * key-value store
	 */
	protected Map<String, V> store = null;

	/**
	 * logger
	 */
	@JsonIgnore
	transient private Logger log = getLogger();

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
		getLogger();
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
			getLogger();
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
			getLogger();
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

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("store: " + store);
		return sb.toString();
	}

	/**
	 * get logger instance.<BR/>
	 */
	private Logger getLogger()
	{
		if (log == null)
		{
			log = LoggerFactory.getLogger(getClass());
		}
		return log;
	}
}
