package com.pineone.icbms.so.iot.resources.context;

import java.util.HashMap;
import java.util.Map;

import com.pineone.icbms.so.resources.context.AGenericContext;

/**
 * Service context class.<BR/>
 * Created by uni4love on 2015. 10. 30..
 */
public class ServiceContext extends AGenericContext<Object>
{
	/**
	 * id
	 */
	private static final String ID = "/so/iot/resource/context/servicecontext";

	/**
	 * name
	 */
	private static final String NAME = "SERVICE_CONTEXT";

	@Override
	protected Map<String, Object> createStore()
	{
		return new HashMap();
	}

	@Override
	public String getId()
	{
		return ID;
	}

	@Override
	public String getName()
	{
		return NAME;
	}
}
