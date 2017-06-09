package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.logic.itf.IDeviceControlDAO;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlForDB;
import com.pineone.icbms.so.interfaces.database.model.key.DeviceControlPK;
import com.pineone.icbms.so.interfaces.database.repository.DeviceControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 5. 15..
 */

@Service
public class DeviceControlDAO implements IDeviceControlDAO {

    @Autowired
    DeviceControlRepository deviceControlRepository;

    @Override
    public DeviceControlForDB retrieveDeviceControl(DeviceControlPK deviceControlPK) {
        return deviceControlRepository.findOne(deviceControlPK);
    }

    @Override
    public List<DeviceControlForDB> retrieveDeviceControlList() {
        return deviceControlRepository.findAll();
    }

    @Override
    public DeviceControlForDB retrieveDeviceControlByDeviceIdAndContextModelId(String deviceId, String contextModelId) {
        return deviceControlRepository.findByIdAndContextModelId(deviceId, contextModelId);
    }
}
