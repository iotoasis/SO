package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceData;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 4. 21..
 */
public interface IDeviceDAO {

    DeviceForDB retrieveDevice(String id);

    List<DeviceForDB> retrieveDeviceList();

    DeviceForDB createDevice(DeviceData deviceData);

    DeviceForDB updateDevice(String id, DeviceData deviceData);

    String deleteDevice(String id);

    List<DeviceForDB> retrieveDeviceList(String functionId, String aspectId, String locationUri);
}
