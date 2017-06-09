package com.pineone.icbms.so.serviceprocessor.util.devicemapper;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;

import java.util.List;

/**
 * VirtualObject-VirtualDevice mappe interface.<BR/>
 *
 * Created by uni4love on 2016. 12. 30..
 */
public interface IDeviceMapper {
    /**
     * from VirtualObject.<BR/>
     *
     * @param virtualObject
     */
    List<IGenericVirtualDevice> getDevices(IGenericVirtualObject virtualObject);
}
