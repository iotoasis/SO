package com.pineone.icbms.so.resources.task;

import java.util.List;

import com.pineone.icbms.so.resources.activity.IActivity;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic task interface.<BR/>
 * Created by uni4love on 2015. 07. 11..
 */
public interface IGenericTask<IGenericActivity extends IActivity> extends ITask<Long, String>
{
	/**
	 * return activity list.<BR/>
	 * 
	 * @return IGenericActivity list
	 */
	List<IGenericActivity> getActivityList();

	/**
	 * return result.<BR/>
	 * @return result
     */
	IGenericResult getResult();
}
