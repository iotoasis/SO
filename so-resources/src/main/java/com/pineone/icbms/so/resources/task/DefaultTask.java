package com.pineone.icbms.so.resources.task;

import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.resources.activity.IGenericActivity;

/**
 * Default task class.<BR/>
 * Created by existmaster on 2015. 10. 30..
 */
public class DefaultTask extends AGenericTask
{
	/**
	 * This method return List of GenericActivity
	 * @return List of GenericActivity
     */
	@Override
	protected List<IGenericActivity> createActivityList()
	{
		return new ArrayList<IGenericActivity>();
	}
}
