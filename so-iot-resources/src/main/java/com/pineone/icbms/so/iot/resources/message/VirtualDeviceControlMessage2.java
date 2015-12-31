package com.pineone.icbms.so.iot.resources.message;

public class VirtualDeviceControlMessage2
{
	String	id;
	String	ontologyref;
	String	devicename;
	String	devicemodel;
	String	operator;
	String	value;

	public VirtualDeviceControlMessage2()
	{
	}

	public VirtualDeviceControlMessage2(String id, String ontologyref,
										String devicename, String devicemodel, String operator,
										String value)
	{
		this.id = id;
		this.ontologyref = ontologyref;
		this.devicename = devicename;
		this.devicemodel = devicemodel;
		this.operator = operator;
		this.value = value;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getOntologyref()
	{
		return ontologyref;
	}

	public void setOntologyref(String ontologyref)
	{
		this.ontologyref = ontologyref;
	}

	public String getDevicename()
	{
		return devicename;
	}

	public void setDevicename(String devicename)
	{
		this.devicename = devicename;
	}

	public String getDevicemodel()
	{
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel)
	{
		this.devicemodel = devicemodel;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
