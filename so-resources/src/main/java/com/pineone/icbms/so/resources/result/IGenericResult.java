package com.pineone.icbms.so.resources.result;

/**
 * Generic result interface.<BR/>
 * Created by uni4love on 2015. 12. 18..
 */
public interface IGenericResult extends IResult<Integer>
{
	/**
	 * status code: success
	 */
	int SC_SUCCESS = 2000;

	/**
	 * status code: failed
	 */
	int SC_FAILED = 4000;

	/**
	 * return status
	 * 
	 * @return status
	 */
	Integer getStatus();
}
