package com.pineone.icbms.so.resources.task;

import java.util.Iterator;
import java.util.List;

import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.result.IGenericResult;
import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic task abstract class.<BR/>
 * Created by uni4love on 2015. 07. 11..
 */
abstract public class AGenericTask extends AGenericVirtualObject
		implements IGenericTask, java.io.Serializable
{
	private static final long serialVersionUID = 3943315680162808989l;

	/**
	 * generic activity list
	 */
	List<IGenericActivity> activityList = null;

	/**
	 * result
	 */
	IGenericResult result = null;

	/**
	 * parent id
	 */
	String parentId = null;

	/**
	 * constructor
	 */
	public AGenericTask()
	{
		activityList = createActivityList();
	}

	@Override
	public List<IGenericActivity> getActivityList()
	{
		return activityList;
	}

	@Override
	public IGenericResult getResult()
	{
		return result;
	}

	public void setActivityList(List<IGenericActivity> activityList) {
		this.activityList = activityList;
	}

	@Override
	public String getParentId()
	{
		return this.parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * add a activity.<BR/>
	 *
	 * @param activity
	 *            activity
	 */
	public void addActivity(IGenericActivity activity)
	{
		this.activityList.add(activity);
	}

	/**
	 * create and return activity list.<BR/>
	 * 
	 * @return List<IGenericAction>
	 */
	abstract protected List<IGenericActivity> createActivityList();

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(">> task\n");
		sb.append("id: ").append(id);
		sb.append("\nname: ").append(name);
		sb.append("\n");
		Iterator iter = activityList.iterator();
		while (iter.hasNext())
		{
			sb.append(iter.next());
		}
		return sb.toString();
	}
}
