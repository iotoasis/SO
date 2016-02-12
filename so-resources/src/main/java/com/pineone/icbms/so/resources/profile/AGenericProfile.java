package com.pineone.icbms.so.resources.profile;

import java.util.List;

import com.pineone.icbms.so.resources.context.DefaultContext;
import com.pineone.icbms.so.resources.service.DefaultService;
import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic profile abstract class.<BR/>
 * Created by uni4love on 2015. 11. 8..
 */
abstract public class AGenericProfile extends AGenericVirtualObject
		implements IGenericProfile<String, String>
{
	/**
	 * context list
	 */
	protected List<DefaultContext> contextList;

	/**
	 * service list
	 */
	protected List<DefaultService> serviceList;

	/**
	 * return context list.<BR/>
	 * 
	 * @return context list
	 */
	public List<DefaultContext> getContextList()
	{
		return contextList;
	}

	/**
	 * return service list.<BR/>
	 * 
	 * @return service list
	 */
	public List<DefaultService> getServiceList()
	{
		return serviceList;
	}
}
