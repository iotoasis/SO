package com.pineone.icbms.so.resources.registry;

import com.pineone.icbms.so.resources.service.IService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServiceRegistry default class.<BR/>
 * Created by uni4love on 2015. 1. 12..
 */
public class DefaultServiceRegistry<K> extends AGenericServiceRegistry
{
	/**
	 * registry name
	 */
	private static String name = "DEFAULT SERVICE REGISTRY";

	/**
	 * constructor
	 */
	public DefaultServiceRegistry()
	{
	}

	/**
	 * return ServiceRegistry singleton instance<BR/>
	 *
	 * @return ServiceRegistry singleton instance
	 */
	public static final DefaultServiceRegistry getInstance()
	{
		return SingletonHolder.singleton;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	protected Map createStore()
	{
		return new ConcurrentHashMap<K, IService>();
	}

	/**
	 * inner singleton instance class
	 */
	private static final class SingletonHolder
	{
		static final DefaultServiceRegistry singleton = new DefaultServiceRegistry();
	}
}
