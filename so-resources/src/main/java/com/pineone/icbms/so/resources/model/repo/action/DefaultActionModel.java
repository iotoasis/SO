package com.pineone.icbms.so.resources.model.repo.action;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Generic action model default class.<BR/>
 * Created by uni4love on 2015. 11. 4..
 */
public class DefaultActionModel extends AGenericModel implements IActionModel
{
	/**
	 * package name
	 */
	protected String packageName = null;

	@Override
	public String getType()
	{
		return TYPE;
	}

	public String getPackageName()
	{
		return packageName;
	}

	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
}
