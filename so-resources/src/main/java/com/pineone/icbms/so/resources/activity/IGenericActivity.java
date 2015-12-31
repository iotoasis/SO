package com.pineone.icbms.so.resources.activity;

import com.pineone.icbms.so.resources.action.IAction;
import com.pineone.icbms.so.resources.result.IGenericResult;

import java.util.List;

/**
 * Generic activity interface.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
public interface IGenericActivity<IGenericAction extends IAction> extends IActivity<Long, String>
{
	/**
	 * return action list.<BR/>
	 * 
	 * @return IGenericAction list
	 */
	List<IGenericAction> getActionList();

	/**
	 * return result.<BR/>
	 * @return result
     */
	IGenericResult getResult();
}
