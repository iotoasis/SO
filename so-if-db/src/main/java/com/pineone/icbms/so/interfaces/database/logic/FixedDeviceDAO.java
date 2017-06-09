package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.FixedDeviceData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IFixedDeviceDAO;
import com.pineone.icbms.so.interfaces.database.model.FixedDeviceForDB;
import com.pineone.icbms.so.interfaces.database.repository.FixedDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2017. 3. 29..
 */

//  지정 디바이스 Data Access 기능 구현
@Service
public class FixedDeviceDAO implements IFixedDeviceDAO {
    //
    @Autowired
    FixedDeviceRepository fixedDeviceRepository;

    // 지정 디바이스 단일 조회 기능
    @Override
    public FixedDeviceForDB retrieveFixedDevice(String id) {
        //
        return fixedDeviceRepository.findOne(id);
    }

    // 지정 디바이스 전체 조회
    @Override
    public List<FixedDeviceForDB> retrieveFixedDeviceList() {
        //
        return fixedDeviceRepository.findAll();
    }

    //  지정 디바이스 데이터 생성
    @Override
    public String createFixedDevice(FixedDeviceData fixedDeviceData) {
        //
        return null;
    }

    //  지정 디바이스 데이터 갱신
    @Override
    public String updateFixedDevice(String id, FixedDeviceData fixedDeviceData) {
        //
        return null;
    }

    // 지정 디바이스 데이터 삭제
    @Override
    public String deleteFixedDevice(String id) {
        //
        FixedDeviceForDB fixedDeviceForDB = fixedDeviceRepository.findOne(id);
        fixedDeviceRepository.delete(id);
        String message = "Delete  " + fixedDeviceForDB.toString();
        return message;
    }
}
