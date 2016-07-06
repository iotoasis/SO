package com.pineone.icbms.so.resources.model.repo.task;

import java.util.List;

import com.pineone.icbms.so.resources.model.AGenericModel;
import com.pineone.icbms.so.resources.model.repo.activity.DefaultActivityModel;
import com.pineone.icbms.so.resources.task.DefaultTask;

/**
 * Task model default class.<BR/>
 * Created by uni4love on 2015. 11. 4..
 */
public class DefaultTaskModel extends AGenericModel
		implements ITaskModel<DefaultActivityModel>
{
	/**
	 * task list
	 */
	List<DefaultActivityModel> activityModelList = null;


	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public List<DefaultActivityModel> getActivityModelList()
	{
		return activityModelList;
	}
}
