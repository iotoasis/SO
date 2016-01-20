package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.iot.resources.device.DefaultDeviceInformation;

/**
 * DefaultDeviceInformation model class for repository.<BR/>
 * Created by uni4love on 2015. 12. 7..
 */
public class DeviceInformationModel extends DefaultDeviceInformation
		implements IDeviceInformationModel<String, String>
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
	/**
	 * type
	 */
	protected String type = TYPE;

	/**
	 * created date
	 */
	protected String createdDate = null;

	/**
	 * modified date
	 */
	protected String modifiedDate = null;

	/**
	 * decription
	 */
	protected String description = null;

	@Override
	public String getType()
	{
		return type;
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

	@Override
	public String toString() {
		return "DeviceInformationModel{" +
				"type='" + type + '\'' +
				", createdDate='" + createdDate + '\'' +
				", modifiedDate='" + modifiedDate + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
