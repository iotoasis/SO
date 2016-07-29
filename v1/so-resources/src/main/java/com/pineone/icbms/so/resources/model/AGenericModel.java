package com.pineone.icbms.so.resources.model;

import java.util.Date;

/**
 * Generic model object.<BR/>
 * Created by uni4love on 2015. 10. 7..
 */
abstract public class AGenericModel implements IGenericModel
{
	/**
	 * id
	 */
	protected String id = null;

	/**
	 * name
	 */
	protected String name = null;

	/**
	 * type
	 */
	protected String type = null;

	/**
	 * created date
	 */
	protected Date createdDate = null;

	/**
	 * modified date
	 */
	protected Date modifiedDate = null;

	/**
	 * description
	 */
	protected String description = null;

	/**
	 * status
	 */
	protected String status = null;

	@Override
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	@Override
	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("\nid: ").append(id);
		sb.append("\nname: ").append(name);
		sb.append("\ntype: ").append(type);
		sb.append("\ncreatedDate: ").append(createdDate);
		sb.append("\nmodifiedDate: ").append(modifiedDate);
		sb.append("\ndescription: ").append(description);
		sb.append("\nstatus: ").append(status);
		return sb.toString();
	}
}
