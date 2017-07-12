package com.pineone.icbms.so.interfaces.database.dao;

import com.pineone.icbms.so.interfaces.database.model.FixedDeviceForDB;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */
@Component
public class FixedDeviceDao extends AbstractDao {
    //
    public FixedDeviceForDB retrieveFixedDevice(String id) {
        return null;
    }

    public List<FixedDeviceForDB> retrieveFixedDeviceList() {
        return null;
    }

//    public String createFixedDevice(FixedDeviceData fixedDeviceData) {
//        return null;
//    }
//
//    public String updateFixedDevice(String id, FixedDeviceData fixedDeviceData) {
//        return null;
//    }

    public String deleteFixedDevice(String id) {
        return null;
    }
}
