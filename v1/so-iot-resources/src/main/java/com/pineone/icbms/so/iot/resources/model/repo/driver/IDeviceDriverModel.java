package com.pineone.icbms.so.iot.resources.model.repo.driver;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Device driver model interface.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public interface IDeviceDriverModel<MODEL_TYPE extends String, DATE extends String>
		extends IGenericModel
{
	/**
	 * type: so/resource/devicedriver
	 */
	String TYPE = "so/resource/devicedriver";

	/**
	 * return package name.<BR/>
	 * 
	 * @return package name
	 */
	String getPackageName();
}
