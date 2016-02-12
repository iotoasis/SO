package com.pineone.icbms.so.iot.resources.model.repo.occurrence;

import java.util.List;

import com.pineone.icbms.so.resources.domain.DefaultDomain;
import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Occurrence model class for repository.<BR/>
 * Created by uni4love on 2015. 12. 7..
 */
public class OccurrenceModel extends AGenericModel implements IOccurrenceModel
{
	/**
	 * command
	 */
	protected String cmd;

	/**
	 * context id
	 */
	protected String contextId;

	/**
	 * domain list
	 */
	protected List<DefaultDomain> domainList;

	/**
	 * occurred time
	 */
	protected String time;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public String getContextId()
	{
		return contextId;
	}

	@Override
	public void setContextId(String contextId)
	{
		this.contextId = contextId;
	}

	@Override
	public List<DefaultDomain> getDomainList()
	{
		return domainList;
	}

	@Override
	public void setDomainList(List<DefaultDomain> domainList)
	{
		this.domainList = domainList;
	}

	@Override
	public String getCmd()
	{
		return cmd;
	}

	@Override
	public void setCmd(String cmd)
	{
		this.cmd = cmd;
	}

	@Override
	public String getTime()
	{
		return time;
	}

	@Override
	public void setTime(String time)
	{
		this.time = time;
	}
}
