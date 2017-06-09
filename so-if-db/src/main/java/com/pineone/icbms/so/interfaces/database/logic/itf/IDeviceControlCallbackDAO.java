package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceControlCallbackData;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IDeviceControlCallbackDAO {
    //
    DeviceControlCallbackForDB retrieveDeviceControlCallback(String id);

    List<DeviceControlCallbackForDB> retrieveDeviceControlCallbackList();

    String createDeviceControlCallback(DeviceControlCallbackData deviceControlCallbackData);

    String updateDeviceControlCallback(String id, DeviceControlCallbackData deviceControlCallbackData);

    String deleteDeviceControlCallback(String id);
}
