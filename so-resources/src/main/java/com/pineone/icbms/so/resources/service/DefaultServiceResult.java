package com.pineone.icbms.so.resources.service;

import com.pineone.icbms.so.resources.result.AGenericResult;

/**
 * Service result class.<BR/>
 * Created by uni4love on 2015. 11. 01..
 */
public class DefaultServiceResult extends AGenericResult
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
