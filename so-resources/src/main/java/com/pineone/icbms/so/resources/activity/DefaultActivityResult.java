package com.pineone.icbms.so.resources.activity;

import com.pineone.icbms.so.resources.result.AGenericResult;

/**
 * Activity result class.<BR/>
 * Created by uni4love on 2015. 11. 01..
 */
public class DefaultActivityResult extends AGenericResult
{
	/**
	 * status
	 */
	protected int status = SC_SUCCESS;

	@Override
	public Integer getStatus()
	{
		return status;
	}
}
