package com.pineone.icbms.so.serviceprocessor.util.devicemapper;

import com.pineone.icbms.so.serviceprocessor.util.devicemapper.IDeviceMapper;
import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.virtualobject.IGenericVirtualObject;

import java.util.List;

/**
 * VirtualObject-VirtualDevice mapper.<BR/>
 *
 * Created by uni4love on 2016. 12. 30..
 */
public class DeviceMapper implements IDeviceMapper {

    @Override
    public List<IGenericVirtualDevice> getDevices(IGenericVirtualObject virtualObject) {
        return null;
    }
}
