package com.pineone.icbms.so.resources.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service class.<BR/>
 * Created by uni4love on 2015. 11. 20..
 */
public class DefaultService<V> extends AGenericService
{
	@Override
	protected Map createStore()
	{
		return new ConcurrentHashMap<String, V>();
	}
}
