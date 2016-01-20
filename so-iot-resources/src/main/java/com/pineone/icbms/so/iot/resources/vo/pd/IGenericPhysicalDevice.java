package com.pineone.icbms.so.iot.resources.vo.pd;

/**
 * Generic physical device interface.<BR/>
 * Created by uni4love on 2015. 10. 7..
 */
public interface IGenericPhysicalDevice extends IPhysicalDevice
{
    /**
     * return uri.<BR/>
     * @return uri
     */
	String getUri();

    /**
     * return device information.<BR/>
     * @return devicec information
     */
	String getDeviceInformationId();
}
