package com.pineone.icbms.so.resources.registry;

/**
 * Key-value Registry interface<BR/>
 * Created by uni4love on 2010. 1. 15..
 */
public interface IKeyValueRegistry<K, V> extends IRegistry
{
	/**
	 * register service<BR/>
	 * 
	 * @param key
	 *            key for value
	 * @param value
	 *            value
	 */
	void addValue(K key, V value);

	/**
	 * remove key-value<BR/>
	 * 
	 * @param key
	 *            key: class name(ex: class name with full package name)
	 * @return removed value
	 */
	V removeValue(K key);

	/**
	 * get value<BR/>
	 * 
	 * @param key
	 *            key for value
	 * @return value
	 */
	V getValue(K key);
}
