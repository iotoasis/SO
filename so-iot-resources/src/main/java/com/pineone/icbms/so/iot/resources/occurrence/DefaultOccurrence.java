package com.pineone.icbms.so.iot.resources.occurrence;

import com.pineone.icbms.so.resources.domain.DefaultDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultOccurrence class.<BR/>
 * message format:<BR/>
 * {"cmd":"occ",<BR/>
 * "contextId":"cm101",<BR/>
 * "domains":[
 *    { "id":"xxx.xxx.xx/xx/xx/classroom101",<BR/>
 *      "conditions":null,<BR/>
 *      "tasks":null<BR/>
 *    }
 * ],<BR/>
 * "time":"20151020T153028"}<BR/>
 *
 * Created by Melvin on 15. 10. 21..
 */
public class DefaultOccurrence extends AGenericOccurrence
{
	/**
	 * cmd : distinguish emergency and schedule<BR/>
	 * - occ-emergency : emergency situation occur<BR/>
	 * - occ-schedule : scheduled occur<BR/>
	 * contextId : registered context ID that appointed with SDA in advance  <BR/>
	 * time : Occurrence happen Time (yyyyMMdd’T’HHmmss)<BR/>
	 * domains : Location information of occurrence <BR/>
	 * occurrenceId : Occurrence's ID = " contextID + time "<BR/>
	 */
	protected String cmd;

	/**
	 * context id
	 */
	protected String contextId;

	/**
	 * domain list
	 */
	protected List<DefaultDomain> domains;

	/**
	 * occurred time
	 */
	protected String time;

	/**
	 * constructor
	 */
	public DefaultOccurrence()
	{
	}

    public DefaultOccurrence(String cmd)
    {
        this.cmd = cmd;
    }

	/**
	 * constructor<BR/>
	 * 
	 * @param cmd
	 *            command
	 * @param domains
	 *            domain list
	 * @param time
	 *            occurred time
	 */
	public DefaultOccurrence(String cmd, List<DefaultDomain> domains,
			String time)
	{
		this.cmd = cmd;
		this.domains = domains;
		this.time = time;
	}
	/**
	 * constructor<BR/>
	 *
     *
     *
	 * @param cmd
	 *            command
	 * @param contextId
	 *            context id
	 * @param domains
	 *            domain list
	 */
	public DefaultOccurrence(String cmd, String contextId,
			List<DefaultDomain> domains)
	{
		this.cmd = cmd;
		this.domains = domains;
		this.contextId = contextId;
	}

	/**
	 * constructor<BR/>
	 * 
	 * @param cmd
	 *            command
	 * @param contextId
	 *            context id
	 * @param time
	 *            occurred time
	 */
	public DefaultOccurrence(String cmd, String contextId, String time)
	{
		this.cmd = cmd;
		this.contextId = contextId;
		this.time = time;
	}

	/**
	 * constructor<BR/>
	 * 
	 * @param cmd
	 *            command
	 * @param contextId
	 *            context id
	 * @param domains
	 *            domain list
	 * @param time
	 *            occurred time
	 */
	public DefaultOccurrence(String cmd, String contextId,
			List<DefaultDomain> domains, String time)
	{
		this.cmd = cmd;
		this.contextId = contextId;
		this.domains = domains;
		this.time = time;

	}

    /**
     * add Domain for Test
     *
     * @param domain
     * @return
     */
	public List<DefaultDomain> addDomains(DefaultDomain domain)
	{
		if (this.domains == null)
			this.domains = new ArrayList<DefaultDomain>();
		domains.add(domain);
		return domains;
	}

	public void setDomains(List<DefaultDomain> domains)
	{
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

	@Override
	public String getContextId()
	{
		return contextId;
	}

    @Override
    public List<DefaultDomain> getDomainList() {
        return domains;
    }

    public void setContextId(String contextId)
	{
		this.contextId = contextId;
	}


	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nid: ").append(this.id);
        sb.append("\nname: ").append(this.name);
        sb.append("\ncmd: ").append(this.cmd);
        sb.append("\ncontextId: ").append(this.contextId);
        sb.append("\ndomains: ").append(this.domains);
        sb.append("\ntime: ").append(this.time);

        return sb.toString();
    }

}
