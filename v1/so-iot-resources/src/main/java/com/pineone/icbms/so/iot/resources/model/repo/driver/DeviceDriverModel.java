package com.pineone.icbms.so.iot.resources.model.repo.driver;

import com.pineone.icbms.so.resources.model.AGenericModel;

/**
 * Device driver model class for repository.<BR/>
 * Created by pahnj on 2016-01-04.
 */
public class DeviceDriverModel extends AGenericModel
		implements IDeviceDriverModel<String, String>
{
	/**
	 * package name
	 */
	protected String packageName;

	@Override
	public String getType()
	{
		return TYPE;
	}

	@Override
	public String getPackageName()
	{
		return packageName;
	}

}
