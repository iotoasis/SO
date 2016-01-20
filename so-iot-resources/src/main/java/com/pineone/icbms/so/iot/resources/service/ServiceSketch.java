package com.pineone.icbms.so.iot.resources.service;

import java.util.List;

import com.pineone.icbms.so.resources.domain.DefaultDomain;

/**
 * Service information class for creating a service.<BR/>
 * Created by uni4love on 2016. 12. 18..
 */
public class ServiceSketch
{
	/**
	 * service model id list
	 */
	protected List<String> serviceModelIdList = null;

	/**
	 * domain list
	 */
	protected List<DefaultDomain> domainList = null;

	/**
	 * domain type
	 */
	protected String domainType = null;

	public List<DefaultDomain> getDomainList()
	{
		return domainList;
	}

	public void setDomainList(List<DefaultDomain> domainList)
	{
		this.domainList = domainList;
	}

	public String getDomainType()
	{
		return domainType;
	}

	public void setDomainType(String domainType)
	{
		this.domainType = domainType;
	}

	public List<String> getServiceModelIdList()
	{
		return serviceModelIdList;
	}

	public void setServiceModelIdList(List<String> serviceModelIdList)
	{
		this.serviceModelIdList = serviceModelIdList;
	}
}
