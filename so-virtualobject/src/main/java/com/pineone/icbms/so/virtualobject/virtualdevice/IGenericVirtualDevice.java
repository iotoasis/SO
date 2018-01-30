package com.pineone.icbms.so.virtualobject.virtualdevice;

/**
 * generic virtual devicemapper interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 11..
 */
public interface IGenericVirtualDevice extends IVirtualDevice {
    /**
     * driver info for devicemapper
     *
     * @return device driver info for devicemapper
     */
    String getDriverClassName();

}
