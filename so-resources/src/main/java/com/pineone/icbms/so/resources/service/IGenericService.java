package com.pineone.icbms.so.resources.service;

import com.pineone.icbms.so.resources.domain.IDomain;
import com.pineone.icbms.so.resources.model.repo.context.IContextModel;
import com.pineone.icbms.so.resources.result.IResult;
import com.pineone.icbms.so.resources.task.ITask;

/**
 * generic service interface.<BR/>
 * Created by uni4love on 2015. 06. 14..
 */
public interface IGenericService<IGenericContextModel extends IContextModel, IGenericTask extends ITask, IGenericDomain extends IDomain, IGenericResult extends IResult>
		extends IService
{
	/**
	 * return context model.<BR/>
	 * 
	 * @return IGenericContextModel
	 */
	IGenericContextModel getContextModel();

	/**
	 * return task.<BR/>
	 * 
	 * @return IGenericTask
	 */
	IGenericTask getTask();

	/**
	 * return domain.<BR/>
	 * 
	 * @return IGenericDomain
	 */
	IGenericDomain getDomain();

	/**
	 * return result.<BR/>
	 * 
	 * @return IGenericResult
	 */
	IGenericResult getResult();
}
