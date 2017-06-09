package com.pineone.icbms.so.interfaces.database.logic.itf;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.FixedDeviceData;
import com.pineone.icbms.so.interfaces.database.model.FixedDeviceForDB;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
public interface IFixedDeviceDAO {
    //
    FixedDeviceForDB retrieveFixedDevice(String id);

    List<FixedDeviceForDB> retrieveFixedDeviceList();

    String createFixedDevice(FixedDeviceData fixedDeviceData);

    String updateFixedDevice(String id, FixedDeviceData fixedDeviceData);

    String deleteFixedDevice(String id);
}
