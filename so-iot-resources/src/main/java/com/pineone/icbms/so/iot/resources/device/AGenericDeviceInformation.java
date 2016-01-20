package com.pineone.icbms.so.iot.resources.device;

import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Device informat abstract class.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public class AGenericDeviceInformation extends AGenericVirtualObject
		implements IGenericDeviceInformation<String, String>
{
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
	String deviceDriverID;

	@Override
	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	@Override
	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	@Override
	public String getMf()
	{
		return mf;
	}

	public void setMf(String mf)
	{
		this.mf = mf;
	}

	@Override
	public String getDeviceDriverId()
	{
		return deviceDriverID;
	}

	public void setDeviceDriverId(String deviceDriverId)
	{
		this.deviceDriverID = deviceDriverId;
	}

}
