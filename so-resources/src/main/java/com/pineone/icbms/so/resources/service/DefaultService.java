package com.pineone.icbms.so.resources.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.task.IGenericTask;

/**
 * Service default class.<BR/>
 * Created by uni4love on 2015. 11. 20..
 */
public class DefaultService<V> extends AGenericService
{
	private static final long serialVersionUID = 7809272501814934515l;

	@Override
	protected Map createStore()
	{
		return new ConcurrentHashMap<String, V>();
	}

	public void addTask(IGenericTask task)
	{
		this.taskList.add(task);
	}
}
