package com.pineone.icbms.so.resources.registry;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pineone.icbms.so.resources.service.IService;

/**
 * Generic service registry<BR/>
 * Created by uni4love on 2010. 3. 28..
 */
public abstract class AGenericServiceRegistry<K> implements IServiceRegistry<K>
{
	/**
	 * services store
	 */
	protected Map<K, IService> store = null;
	/**
	 * logger
	 */
	private Logger log = LoggerFactory.getLogger(AGenericServiceRegistry.class);

	/**
	 * constructor
	 */
	public AGenericServiceRegistry()
	{
		store = createStore();
	}

	/**
	 * register a service<BR/>
	 *
	 * @param service
	 *            service
	 */
	public synchronized void registerService(IService service)
	{
		registerService((K) service.getName(), service);
	}

	/**
	 * register a service<BR/>
	 * 
	 * @param service
	 *            service
	 */
	@Override
	public synchronized void registerService(K key, IService service)
	{
		if (store.containsKey(key))
		{
			log.warn(
					"The service key exists already in service registry: {}, this item is skipped.",
					service.getName());
		}
		else
		{
			store.put(key, service);
			log.info("The service registered to service registry: {}", key);
		}
	}

	/**
	 * unregister a service<BR/>
	 * 
	 * @param key
	 *            service key(ex: class name with full package name)
	 * @return unregistered service
	 */
	@Override
	public synchronized IService unregisterService(K key)
	{
		if (!store.containsKey(key))
		{
			log.warn("The service NOT exists already in service registry: {}",
					key);
		}
		return store.remove(key);
	}

	/**
	 * return service by service key<BR/>
	 * 
	 * @param key
	 *            service key(ex: class name with full package name)
	 * @return service
	 */
	@Override
	public IService getService(K key)
	{
		if (!store.containsKey(key))
		{
			log.warn("The service NOT exists: {}", key);
		}
		return store.get(key);
	}

	/**
	 * return interface for returning Store<BR/>
	 * 
	 * @return Map
	 */
	abstract protected Map<K, IService> createStore();
}
