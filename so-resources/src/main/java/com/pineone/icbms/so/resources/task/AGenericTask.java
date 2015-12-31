package com.pineone.icbms.so.resources.task;

import java.util.List;

import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic task abstract class.<BR/>
 * Created by uni4love on 2015. 07. 11..
 */
abstract public class AGenericTask implements IGenericTask
{
	/**
	 * id
	 */
	long id;

	/**
	 * name
	 */
	String name;

	/**
	 * generic activity list
	 */
	List<IGenericActivity> activityList;

	/**
	 * result
	 */
	IGenericResult result;

	@Override
	public Long getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<IGenericActivity> getActivityList()
	{
		return activityList;
	}

	@Override
	public IGenericResult getResult()
	{
		return result;
	}
}
