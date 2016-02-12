package com.pineone.icbms.so.iot.resources.model.repo.device;

import com.pineone.icbms.so.resources.model.IGenericModel;

/**
 * Device information model interface.<BR/>
 * Created by uni4love on 2015. 11. 5..
 */
public interface IDeviceInformationModel extends IGenericModel
{
	/**
	 * type: so/resource/deviceinformation
	 */
	String TYPE = "so/resource/deviceinformation";

	/**
	 * return model.<BR/>
	 * 
	 * @return model
	 */
	String getModel();

	/**
	 * set model,<BR/>
	 * 
	 * @param model
	 *            model
	 */
	void setModel(String model);

	/**
	 * return sn.<BR/>
	 * 
	 * @return sn
	 */
	String getSn();

	/**
	 * set sn.<BR/>
	 * 
	 * @param sn
	 *            sn
	 */
	void setSn(String sn);

	/**
	 * return mf.<BR/>
	 * 
	 * @return mf
	 */
	String getMf();

	/**
	 * set mf.<BR/>
	 * 
	 * @param mf
	 *            mf
	 */
	void setMf(String mf);

	/**
	 * return device driver id.<BR/>
	 * 
	 * @return device driver id
	 */
	String getDeviceDriverId();

	/**
	 * set device driver id.<BR/>
	 * 
	 * @param deviceDriverId
	 *            device driver id
	 */
	void setDeviceDriverId(String deviceDriverId);
}
