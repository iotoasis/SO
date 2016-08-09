package com.pineone.icbms.so.resources.model.repo.task;

import java.util.List;

import com.pineone.icbms.so.resources.model.IGenericModel;
import com.pineone.icbms.so.resources.model.repo.activity.IActivityModel;

/**
 * Task model interface for repository.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface ITaskModel<ACTIVITY_MODEL extends IActivityModel>
		extends IGenericModel
{
	/**
	 * type: task
	 */
	String TYPE = "so/resource/task";

	/**
	 * return activity model list.<BR/>
	 * 
	 * @return activity model list
	 */
	List<ACTIVITY_MODEL> getActivityModelList();
}
