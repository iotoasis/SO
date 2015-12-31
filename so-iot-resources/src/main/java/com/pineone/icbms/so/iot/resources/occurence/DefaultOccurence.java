package com.pineone.icbms.so.iot.resources.occurence;

import com.pineone.icbms.so.resources.domain.IDomain;
import com.pineone.icbms.so.resources.vo.VirtualObject;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultOccurence class.<BR/>
 * Created by Melvin on 15. 10. 21..
 */
public class DefaultOccurence extends AGenericOccurence
{
	String				cmd;
	String				occurrenceID;
	String              contextModelId;
	List<IDomain>	    domains;
	String				time;

	public DefaultOccurence()
	{

	}

	public DefaultOccurence(String cmd, List<IDomain> domains,
			String time)
	{
		this.cmd = cmd;
		this.domains = domains;
		this.time = time;
	}

	public DefaultOccurence(String cmd, String contextID,
			List<IDomain> domains)
	{
		this.cmd = cmd;
		this.domains = domains;
		this.contextModelId = contextID;
	}

	public DefaultOccurence(String cmd, String contextModelId, String time)
	{
		this.cmd = cmd;
		this.contextModelId = contextModelId;
		this.time = time;
	}

	public DefaultOccurence(String cmd, String contextModelId,
			List<IDomain> domains, String time)
	{
		this.cmd = cmd;
		this.contextModelId = contextModelId;
		this.domains = domains;
		this.time = time;

	}

	public DefaultOccurence(String cmd, String occurrenceID, String contextModelId,
			List<IDomain> domains, String time)
	{
		this.cmd = cmd;
		this.occurrenceID = occurrenceID;
		this.contextModelId = contextModelId;
		this.time = time;
		this.domains = domains;
	}

	public String getCmd()
	{
		return cmd;
	}

	public void setCmd(String cmd)
	{
		this.cmd = cmd;
	}

	public String getOccurrenceID()
	{
		return occurrenceID;
	}

	public void setOccurrenceID(String occurrenceID)
	{
		this.occurrenceID = occurrenceID;
	}

	public void setContextModelId(String contextModelId)
	{
		this.contextModelId = contextModelId;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public void setDomains(List<IDomain> domains)
	{
		this.domains = domains;
	}

	public List<IDomain> addDomains(IDomain domain)
	{
		if (this.domains == null)
			this.domains = new ArrayList<IDomain>();
		    domains.add(domain);
		    return domains;
	}

	@Override
	public String getContextModelId() {
		return null;
	}

	@Override
	public List<IDomain> getDomainList() {
		return null;
	}

	/**
	 * {"cmd":"occ","contextId":"cm101","domains":[{"id":
	 * "xxx.xxx.xx/xx/xx/classroom101","conditions":null,"tasks":null}],"time":
	 * "20151020T153028"}
	 */
}
