package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceControlCallbackData;
import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceControlData;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.key.DeviceControlPK;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */
public interface IDeviceControlDAO {
    //
    DeviceControlForDB retrieveDeviceControl(DeviceControlPK deviceControlPK);

    List<DeviceControlForDB> retrieveDeviceControlList();

    DeviceControlForDB retrieveDeviceControlByDeviceIdAndContextModelId(String deviceId, String contextModelId);
}
