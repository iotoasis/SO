package com.pineone.icbms.so.serviceutil.interfaces.provider;

import com.pineone.icbms.so.virtualobject.virtualdevice.IGenericVirtualDevice;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;

import java.util.List;

/**
 * DeviceControl provider interface.<BR/>
 *
 * Created by uni4love on 2017. 1. 6..
 */
public interface IDeviceControlProvider extends IProvider<IGenericVirtualDevice, String> {
    List<DeviceForDB> getDeviceList(String function, String aspect, String locationUri);
}
