package com.pineone.icbms.so.resources.activity;

import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.result.IGenericResult;

import java.util.List;

/**
 * generic activity abstract class.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
abstract public class AGenericActivity implements IGenericActivity
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
	 * action list
	 */
	List<IGenericAction> actionList = null;

	/**
	 * result
	 */
	private IGenericResult result;

	@Override
	public Long getId()
	{
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<IGenericAction> getActionList()
	{
		return actionList;
	}


	@Override
	public IGenericResult getResult() {
		return result;
	}

}
