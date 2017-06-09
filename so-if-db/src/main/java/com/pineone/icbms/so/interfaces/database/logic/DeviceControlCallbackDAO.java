package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceControlCallbackData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IDeviceControlCallbackDAO;
import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;
import com.pineone.icbms.so.interfaces.database.repository.DeviceControlCallbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

// 디바이스제어내용 Data Access 기능 구현
@Service
public class DeviceControlCallbackDAO implements IDeviceControlCallbackDAO {
    //
    @Autowired
    DeviceControlCallbackRepository deviceControlCallbackRepository;

    //  디바이스 제어내용 단일 조회
    @Override
    public DeviceControlCallbackForDB retrieveDeviceControlCallback(String id) {
        //
        return deviceControlCallbackRepository.findOne(id);
    }

    // 디바이스 제어내용 전체 조회
    @Override
    public List<DeviceControlCallbackForDB> retrieveDeviceControlCallbackList() {
        //
        return deviceControlCallbackRepository.findAll();
    }

    // 디바이스 제어내용 생성
    @Override
    public String createDeviceControlCallback(DeviceControlCallbackData deviceControlCallbackData) {
        //
        return null;
    }

    // 디바이스 제어내용 갱신
    @Override
    public String updateDeviceControlCallback(String id, DeviceControlCallbackData deviceControlCallbackData) {
        //
        return null;
    }

    // 디바이스 제어내용 삭제
    @Override
    public String deleteDeviceControlCallback(String id) {
        //
        DeviceControlCallbackForDB deviceControlCallbackForDB = deviceControlCallbackRepository.findOne(id);
        deviceControlCallbackRepository.delete(id);
        String resultMessage = "Delete  " + deviceControlCallbackForDB.toString();
        return resultMessage;
    }
}
