package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Device information model interface.<BR/>
 * Created by uni4love on 2015. 11. 5..
 */
public interface IDeviceInformationModel<MODEL_TYPE extends String, DATE extends String>
		extends IGenericModel
{
	/**
	 * type: so/resource/deviceinformation
	 */
	String TYPE = "so/resource/deviceinformation";
}
