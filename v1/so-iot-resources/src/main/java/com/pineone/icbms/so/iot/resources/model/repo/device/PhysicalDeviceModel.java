package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Physical device model for repository.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public class PhysicalDeviceModel extends AGenericModel
		implements IPhysicalDeviceModel
{
	/**
	 * device information id
	 */
	protected String deviceInformationId = null;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public String getDeviceInformationId()
	{
		return deviceInformationId;
	}
}
