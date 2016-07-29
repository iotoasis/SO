package com.pineone.icbms.so.resources.service;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pineone.icbms.so.resources.context.AGenericContext;
import com.pineone.icbms.so.resources.domain.IGenericDomain;
import com.pineone.icbms.so.resources.result.IGenericResult;
import com.pineone.icbms.so.resources.task.IGenericTask;

/**
 * Generic service abstract class.<BR/>
 * Created by uni4love on 2015. 06. 14..
 */
abstract public class AGenericService<V> extends AGenericContext<V>implements
		IGenericService<IGenericTask, IGenericDomain, IGenericResult, V>,
		java.io.Serializable
{
	private static final long serialVersionUID = -1499639462866642534l;

	/**
	 * task
	 */

	protected List<IGenericTask> taskList = null;

	/**
	 * domain
	 */
	protected IGenericDomain domain = null;

	/**
	 * result
	 */
	protected IGenericResult result = null;

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
	public List<IGenericTask> getTaskList()
	{
		return taskList;
	}

	public void setTaskList(List<IGenericTask> taskList)
	{
		this.taskList = taskList;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("> service\n");
		sb.append(super.toString());
		sb.append("\ndomain: ").append(domain);
		sb.append("\ntask list: size = " + taskList.size() + "\n");
		Iterator<IGenericTask> iter = taskList.iterator();
		while (iter.hasNext())
		{
			sb.append(iter.next());
		}
		return sb.toString();
	}
}
