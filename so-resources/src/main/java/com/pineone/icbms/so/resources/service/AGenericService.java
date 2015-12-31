package com.pineone.icbms.so.resources.service;

import com.pineone.icbms.so.resources.context.AGenericContext;
import com.pineone.icbms.so.resources.domain.IGenericDomain;
import com.pineone.icbms.so.resources.model.repo.context.IGenericContextModel;
import com.pineone.icbms.so.resources.result.IGenericResult;
import com.pineone.icbms.so.resources.task.IGenericTask;

/**
 * Generic service abstract class.<BR/>
 * Created by uni4love on 2015. 06. 14..
 */
abstract public class AGenericService extends AGenericContext
		implements IGenericService
{
	/**
	 * id
	 */
	protected String id;

	/**
	 * name
	 */
	protected String name;

	/**
	 * context model
	 */
	protected IGenericContextModel contextModel;

	/**
	 * task
	 */
	protected IGenericTask task;

	/**
	 * domain
	 */
	protected IGenericDomain domain;

	/**
	 * result
	 */
	protected IGenericResult result;

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public IGenericContextModel getContextModel()
	{
		return contextModel;
	}

	public void setContextModel(IGenericContextModel contextModel)
	{
		this.contextModel = contextModel;
	}

	@Override
	public IGenericDomain getDomain()
	{
		return domain;
	}

	public void setDomain(IGenericDomain domain)
	{
		this.domain = domain;
	}

	@Override
	public IGenericResult getResult()
	{
		return result;
	}

	public void setResult(IGenericResult result)
	{
		this.result = result;
	}

	@Override
	public IGenericTask getTask()
	{
		return task;
	}

	public void setTask(IGenericTask task)
	{
		this.task = task;
	}
}
