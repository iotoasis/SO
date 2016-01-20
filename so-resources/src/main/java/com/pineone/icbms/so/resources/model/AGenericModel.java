package com.pineone.icbms.so.resources.model;

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
	protected String createdDate = null;

	/**
	 * modified date
	 */
	protected String modifiedDate = null;

	/**
	 * description
	 */
	protected String description = null;

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
	public String getCreatedDate()
	{
		return createdDate;
	}

	@Override
	public String getModifiedDate()
	{
		return modifiedDate;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return sb.toString();
	}
}
