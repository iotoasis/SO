package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Physical device model interface.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public interface IPhysicalDeviceModel extends IGenericModel
{
	/**
	 * type: so/resource/physicaldevice
	 */
	String TYPE = "so/resource/physicaldevice";

	/**
s	 * return device information Id
	 * 
	 * @return device information id
	 */
	String getDeviceInformationId();
}
