package com.pineone.icbms.so.resources.domain;

/**
 * Created by Melvin on 2016. 1. 6..
 */
public class DefaultDomain extends AGenericDomain
{
	private static final long serialVersionUID = 3664754380402892045l;

	public String dev;

	public String loc;

	public String person_id;

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}

	public String getPerson_id()
	{
		return person_id;
	}

	public void setPerson_id(String person_id)
	{
		this.person_id = person_id;
	}

	public String getDev()
	{
		return dev;
	}

	public void setDev(String dev)
	{
		this.dev = dev;
	}

}
