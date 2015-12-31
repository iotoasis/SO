package com.pineone.icbms.so.resources.model.repo.context;

/**
 * Default context model class.<BR/>
 * Created by Melvin on 15. 10. 21..
 */
public class DefaultContextModel extends AGenericContextModel
{
	/**
	 * domain id
	 */
	String domainId;


	/**
	 * constructor<BR/>
	 */
	public DefaultContextModel()
	{
	}

	/**
	 * constructor<BR/>
	 * 
	 * @param contextModelName
	 *            context model name
	 * @param domainId
	 *            domain id
	 */
	public DefaultContextModel(String contextModelName, String domainId)
	{
		this.name = contextModelName;
		this.domainId = domainId;
	}

	public String getDomainId()
	{
		return domainId;
	}

	public void setDomainId(String domainId)
	{
		this.domainId = domainId;
	}
}
