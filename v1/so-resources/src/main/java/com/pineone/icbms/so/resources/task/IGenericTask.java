package com.pineone.icbms.so.resources.task;

import java.util.List;

import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic task interface.<BR/>
 * Created by uni4love on 2015. 07. 11..
 */
public interface IGenericTask
		extends ITask
{
	/**
	 * return activity list.<BR/>
	 * 
	 * @return GENERIC_ACTIVITY list
	 */
	List<IGenericActivity> getActivityList();

	/**
	 * return result.<BR/>
	 * 
	 * @return result
	 */
	IGenericResult getResult();

	/**
	 * return parent id.<BR/>
	 * 
	 * @return parent id
	 */
	String getParentId();
}
