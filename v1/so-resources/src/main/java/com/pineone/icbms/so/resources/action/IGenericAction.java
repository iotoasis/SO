package com.pineone.icbms.so.resources.action;

import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.result.IGenericResult;

/**
 * Generic action interface.<BR/>
 * Created by uni4love on 2015. 06. 18..
 */
public interface IGenericAction
		extends IAction
{
	/**
	 * execute action content.<BR/>
	 * 
	 * @param context
	 *            context
	 */
	void execute(IGenericContext context);

	/**
	 * return result.<BR/>
	 * 
	 * @return result
	 */
	IGenericResult getResult();
}
