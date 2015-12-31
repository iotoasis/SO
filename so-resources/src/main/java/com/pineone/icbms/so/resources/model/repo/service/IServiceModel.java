package com.pineone.icbms.so.resources.model.repo.service;

import java.util.List;

import com.pineone.icbms.so.resources.model.IModel;
import com.pineone.icbms.so.resources.model.repo.task.ITaskModel;

/**
 * Service model interface for repository.<BR/>
 * Created by uni4love on 2015. 10. 15..
 */
public interface IServiceModel extends IModel
{
	/**
	 * return task model list.<BR/>
	 * 
	 * @return task modedl list.
	 */
	List<ITaskModel> getTaskModelList();
}
