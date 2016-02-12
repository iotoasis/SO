package com.pineone.icbms.so.iot.resources.occurrence;

import com.pineone.icbms.so.resources.domain.DefaultDomain;

import java.util.List;

/**
 * Generic occurrence interface.<BR/>
 * Created by uni4love on 2015. 10. 20..
 */
public interface IGenericOccurrence extends IOccurrence
{
	/**
	 * return context model id.<BR/>
	 * 
	 * @return context model id
	 */
	String getContextId();

	/**
	 * return domain list.<BR/>
	 * 
	 * @return domain list
	 */
	List<DefaultDomain> getDomainList();

	/**
	 * return command.<BR/>
	 * 
	 * @return command
	 */
	String getCmd();

	/**
	 * return time.<BR/>
	 * 
	 * @return time
	 */
	String getTime();
}
