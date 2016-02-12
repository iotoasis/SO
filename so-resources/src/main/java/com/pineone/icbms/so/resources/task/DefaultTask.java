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
	@Override
	protected List<IGenericActivity> createActivityList()
	{
		return new ArrayList<IGenericActivity>();
	}
}
