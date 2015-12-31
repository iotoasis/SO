package com.pineone.icbms.so.iot.resources.device;

/**
 * device infomation class.<BR/>
 * Created by uni4love on 2015. 12. 18..
 */
public class DeviceInformation
{
	/**
	 * id
	 */
	String id;

	/**
	 * name
	 */
	String name;

	/**
	 * model
	 */
	String model;

	/**
	 * serial number
	 */
	String sn;

	/**
	 * manufacturer
	 */
	String mf;

	/**
	 * device driver id
	 */
	String deviceDriverId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getMf()
	{
		return mf;
	}

	public void setMf(String mf)
	{
		this.mf = mf;
	}

	public String getDeviceDriverId()
	{
		return deviceDriverId;
	}

	public void setDeviceDriverId(String deviceDriverId)
	{
		this.deviceDriverId = deviceDriverId;
	}
}
