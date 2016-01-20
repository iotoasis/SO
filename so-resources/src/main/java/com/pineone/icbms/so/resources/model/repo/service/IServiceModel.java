package com.pineone.icbms.so.resources.model.repo.service;

import java.util.List;

import com.pineone.icbms.so.resources.model.IGenericModel;
import com.pineone.icbms.so.resources.model.repo.task.DefaultTaskModel;
import com.pineone.icbms.so.resources.model.repo.task.ITaskModel;

/**
 * Service model interface for repository.<BR/>
 * ¬ Created by uni4love on 2015. 10. 15..
 */
public interface IServiceModel<TASK_MODEL extends ITaskModel> extends IGenericModel
{
	/**
	 * type: service
	 */
	String TYPE = "so/resource/service";

	/**
	 * return task model list.<BR/>
	 * 
	 * @return task model list.
	 */
	List<TASK_MODEL> getTaskModelList();
}
