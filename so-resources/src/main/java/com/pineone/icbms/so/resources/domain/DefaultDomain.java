package com.pineone.icbms.so.resources.domain;

/**
 * Created by Melvin on 2016. 1. 6..
 * Occurrence has DefaultDomain List, use transfer Data with SDA<BR/>
 */
public class DefaultDomain extends AGenericDomain
{
	private static final long serialVersionUID = 3664754380402892045l;

    /**
     * Device Information
     */
	public String dev;

    /**
     * location Information
     */
	public String loc;

    /**
     * Person Information
     */
	public String person_id;

    /**
     * Condition information
     */
    public String cond;

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

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
