package com.pineone.icbms.so.resources.profile;

import java.util.List;

import com.pineone.icbms.so.resources.context.DefaultContext;
import com.pineone.icbms.so.resources.service.DefaultService;

/**
 * Profile default class.<BR/>
 * Created by uni4love on 2015. 11. 8..
 */
public class DefaultProfile extends AGenericProfile
{
	/**
	 * set service list<BR/>
	 * 
	 * @param serviceList
	 *            service list
	 */
	public void setServiceList(List<DefaultService> serviceList)
	{
		this.serviceList = serviceList;
	}

	/**
	 * set context list.<BR/>
	 * 
	 * @param contextList
	 *            context list
	 */
	public void setContextList(List<DefaultContext> contextList)
	{
		this.contextList = contextList;
	}

}
