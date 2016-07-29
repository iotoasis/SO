package com.pineone.icbms.so.iot.resources.message;

public class VirtualDeviceControlMessage
{
	String	id;
	String	ontologyref;
	String	deviceid;
	String	devicemodel;
	String	value;
	String	result1;
	String	result2;

	public VirtualDeviceControlMessage()
	{
	}

	public VirtualDeviceControlMessage(String id, String ontologyref,
			String deviceid, String devicemodel, String value, String result1,
			String result2)
	{
		this.id = id;
		this.ontologyref = ontologyref;
		this.deviceid = deviceid;
		this.devicemodel = devicemodel;
		this.value = value;
		this.result1 = result1;
		this.result2 = result2;
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

	public String getDeviceid()
	{
		return deviceid;
	}

	public void setDeviceid(String devicename)
	{
		this.deviceid = devicename;
	}

	public String getDevicemodel()
	{
		return devicemodel;
	}

	public void setDevicemodel(String devicemodel)
	{
		this.devicemodel = devicemodel;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getResult1()
	{
		return result1;
	}

	public void setResult1(String result1)
	{
		this.result1 = result1;
	}

	public String getResult2()
	{
		return result2;
	}

	public void setResult2(String result2)
	{
		this.result2 = result2;
	}

	/**
	 * { "id":"VDCM-TEST-MESSAGE-001",
	 * "ontologyref":"http://ontology.com/power_control",
	 * "deviceid":"http://www.pineone.com/m2m/SwitchStatusSensor",
	 * "devicemodel":"SS22", "value":"ON", "result1":"", "result2":"" }
	 */

}
