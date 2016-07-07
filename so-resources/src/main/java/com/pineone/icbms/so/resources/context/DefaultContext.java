package com.pineone.icbms.so.resources.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context default class.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public class DefaultContext<V> extends AGenericContext<V>
{
	private static final long serialVersionUID = 5391887906010267171l;

	@Override
	protected Map createStore()
	{
		return new ConcurrentHashMap<String, V>();
	}
}
