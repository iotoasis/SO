package com.pineone.icbms.so.iot.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pineone.icbms.so.resources.registry.AGenericServiceRegistry;
import com.pineone.icbms.so.resources.service.IService;

/**
 * IoT Service registry.<BR/>
 * Created by uni4love on 2015. 11. 30..
 */
public class IoTServiceRegistry extends AGenericServiceRegistry<String>
{
	/**
	 * names
	 */
	private static final String NAME = "IOT_SERVICE_REGISTRY";

	@Override
	protected Map<String, IService> createStore()
	{
		return new ConcurrentHashMap<String, IService>();
	}

	@Override
	public String getName()
	{
		return NAME;
	}
}
