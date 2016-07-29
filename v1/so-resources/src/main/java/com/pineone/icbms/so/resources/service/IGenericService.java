package com.pineone.icbms.so.resources.service;

import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.domain.IGenericDomain;
import com.pineone.icbms.so.resources.result.IGenericResult;
import com.pineone.icbms.so.resources.task.IGenericTask;

import java.util.List;

/**
 * Generic service interface.<BR/>
 * Created by uni4love on 2015. 06. 14..
 */
public interface IGenericService<GENERIC_TASK extends IGenericTask, DOMAIN extends IGenericDomain, GENERIC_RESULT extends IGenericResult, V>
		extends IService, IGenericContext<String, V>
{
	/**
	 * return task.<BR/>
	 * 
	 * @return GENERIC_TASK
	 */
	List<GENERIC_TASK> getTaskList();

	/**
	 * return domain.<BR/>
	 * 
	 * @return DOMAIN
	 */
	DOMAIN getDomain();

	/**
	 * return result.<BR/>
	 * 
	 * @return IGenericResult
	 */
	GENERIC_RESULT getResult();
}
