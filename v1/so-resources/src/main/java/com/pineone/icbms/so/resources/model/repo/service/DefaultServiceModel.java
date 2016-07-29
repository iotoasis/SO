package com.pineone.icbms.so.resources.model.repo.service;

import java.util.List;

import com.pineone.icbms.so.resources.model.AGenericModel;
import com.pineone.icbms.so.resources.model.repo.task.DefaultTaskModel;

/**
 * Service model default class.<BR/>
 * Created by uni4love on 2015. 11. 4..
 */
public class DefaultServiceModel extends AGenericModel
		implements IServiceModel<DefaultTaskModel>
{
	/**
	 * task list
	 */
	protected List<DefaultTaskModel> taskModelList = null;

	@Override
	public String getType()
	{
		return null;
	}

	@Override
	public List<DefaultTaskModel> getTaskModelList()
	{
		return taskModelList;
	}
}
