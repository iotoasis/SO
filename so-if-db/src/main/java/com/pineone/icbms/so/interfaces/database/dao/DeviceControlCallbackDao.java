package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class DeviceControlCallbackDao extends AbstractDao {
    //
    public DeviceControlCallbackForDB retrieveDeviceControlCallback(String id) {
        return null;
    }

    public List<DeviceControlCallbackForDB> retrieveDeviceControlCallbackList() {
        return null;
    }

//    public String createDeviceControlCallback(DeviceControlCallbackData deviceControlCallbackData) {
//        return null;
//    }
//
//    public String updateDeviceControlCallback(String id, DeviceControlCallbackData deviceControlCallbackData) {
//        return null;
//    }

    public String deleteDeviceControlCallback(String id) {
        return null;
    }
}
