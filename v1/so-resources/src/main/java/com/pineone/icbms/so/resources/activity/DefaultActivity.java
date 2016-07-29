package com.pineone.icbms.so.resources.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.property.reference.IGenericOntologyReference;

/**
 * Activity default class.<BR/>
 * Created by Melvin on 2015. 11. 17..
 */
public class DefaultActivity extends AGenericActivity
{
	private static final long serialVersionUID = 3664754380402892048l;

	@Override
	protected List<IGenericAction> createActionList()
	{
		return new ArrayList<IGenericAction>();
	}

	@Override
	protected Map createStore()
	{
		return new ConcurrentHashMap<String, Object>();
	}
}
