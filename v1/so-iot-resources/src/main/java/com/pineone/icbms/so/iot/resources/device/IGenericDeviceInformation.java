package com.pineone.icbms.so.iot.resources.device;

/**
 * Generic device informaion interface.<BR/>
 * Created by uni4love on 2015. 11. 7..
 */
public interface IGenericDeviceInformation<ID extends String, NAME extends String>
		extends IDeviceInformation<ID, NAME>
{
	/**
	 * return model.<BR/>
	 * 
	 * @return model
	 */
	String getModel();

	/**
	 * return sn.<BR/>
	 * 
	 * @return sn
	 */
	String getSn();

	/**
	 * return mf.<BR/>
	 * 
	 * @return mf
	 */
	String getMf();

	/**
	 * return device driver id.<BR/>
	 * 
	 * @return device driver id
	 */
	String getDeviceDriverId();
}
