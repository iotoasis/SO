package com.pineone.icbms.so.iot.resources.vo.pd;

import com.pineone.icbms.so.resources.vo.AGenericVirtualObject;

/**
 * Generic physical device interface.<BR/>
 * Created by uni4love on 2015. 10. 7..
 */
abstract public class AGenericPhysicalDevice extends AGenericVirtualObject
		implements IGenericPhysicalDevice
{
	/**
	 * Device Uri
	 */
	String uri;

	/**
	 * Device Information ID
	 */
	String deviceInformationId;

	@Override
	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	@Override
	public String getDeviceInformationId()
	{
		return deviceInformationId;
	}

	public void setDeviceInformationId(String deviceInformationId)
	{
		this.deviceInformationId = deviceInformationId;
	}
}
