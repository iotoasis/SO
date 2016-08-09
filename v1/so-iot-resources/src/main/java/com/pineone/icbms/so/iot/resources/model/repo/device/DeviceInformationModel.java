package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * DefaultDeviceInformation model class for repository.<BR/>
 * Created by uni4love on 2015. 12. 7..
 */
public class DeviceInformationModel extends AGenericModel
		implements IDeviceInformationModel
{
	/**
	 * model
	 */
	protected String model;

	/**
	 * serial number
	 */
	protected String sn;

	/**
	 * manufacturer
	 */
	protected String mf;

	/**
	 * device driver id
	 */
	protected String deviceDriverID;

	@Override
	public String getType()
	{
		return TYPE;
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
		return deviceDriverID;
	}

	public void setDeviceDriverId(String deviceDriverId)
	{
		this.deviceDriverID = deviceDriverId;
	}
}
