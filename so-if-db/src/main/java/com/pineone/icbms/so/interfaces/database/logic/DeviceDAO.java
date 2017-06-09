package com.pineone.icbms.so.interfaces.database.logic;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.DeviceData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IAspectDAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.IDeviceDAO;
import com.pineone.icbms.so.interfaces.database.logic.itf.ILocationDAO;
import com.pineone.icbms.so.interfaces.database.model.*;
import com.pineone.icbms.so.interfaces.database.repository.DeviceRepository;
import com.pineone.icbms.so.util.id.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Profile Data Access 기능 구현<BR/>
 *
 * Created by melvin on 2017. 4. 21..
 */
@Service
public class DeviceDAO implements IDeviceDAO{

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    FunctionalityDAO functionalityDAO;

    @Autowired
    IAspectDAO aspectDAO;

    @Autowired
    ILocationDAO locationDAO;

    // Device 단일 조회 기능
    @Override
    public DeviceForDB retrieveDevice(String id) {
        return deviceRepository.findOne(id);
    }

    // Device 전채 조회 기능
    @Override
    public List<DeviceForDB> retrieveDeviceList() {
        return deviceRepository.findAll();
    }

    // Device 생성 기능
    @Override
    public DeviceForDB createDevice(DeviceData deviceData) {
        //
        DeviceForDB deviceForDB = createDeviceDataConversion(deviceData);
        deviceForDB.setId("DV" + IdUtils.createRandomUUID());
        deviceRepository.save(deviceForDB);
        return deviceForDB;
    }

    // Device 갱신 기능
    @Override
    public DeviceForDB updateDevice(String id, DeviceData deviceData) {
        //
        DeviceForDB deviceForDB = deviceRepository.findOne(id);
        String createDate = deviceForDB.getCreated_date();
        if( deviceForDB != null){
            deviceForDB = updateDeviceDataConversion(deviceData);
            deviceForDB.setId(id);
            deviceForDB.setCreated_date(createDate);
            deviceRepository.save(deviceForDB);
        } else {
            deviceForDB = createDevice(deviceData);
        }
        return deviceForDB;
    }

    // Device 삭제 기능
    @Override
    public String deleteDevice(String id) {
        //
        DeviceForDB deviceForDB = deviceRepository.findOne(id);
        deviceRepository.delete(id);
        String message = "Delete" + deviceForDB;
        return message;
    }

    @Override
    public List<DeviceForDB> retrieveDeviceList(String functionId, String aspectId, String locationUri){
        //
        List<DeviceForDB> deviceForDBList = new ArrayList<>();
        deviceForDBList = deviceRepository.findByFunctionalityIdAndAspectIdAndLocationId(functionId, aspectId, locationUri);
        return deviceForDBList;
    }

    private DeviceForDB createDeviceDataConversion(DeviceData deviceData){
        //
        FunctionalityForDB functionalityForDB = functionalityDAO.retrieveFunctionality(deviceData.getFunctionalityId());
        AspectForDB aspectForDB = aspectDAO.retrieveAspect(deviceData.getAspectId());
        LocationForDB locationForDB = locationDAO.retrieveLocation(deviceData.getLocationId());
        DeviceForDB deviceForDB = new DeviceForDB(null, deviceData.getName(), deviceData.getDescription(),
                deviceData.getDeviceUri(), functionalityForDB, aspectForDB, locationForDB);
        return deviceForDB;

    }

    private DeviceForDB updateDeviceDataConversion(DeviceData deviceData){
        //
        FunctionalityForDB functionalityForDB = functionalityDAO.retrieveFunctionality(deviceData.getFunctionalityId());
        AspectForDB aspectForDB = aspectDAO.retrieveAspect(deviceData.getAspectId());
        LocationForDB locationForDB = locationDAO.retrieveLocation(deviceData.getLocationId());
        DeviceForDB deviceForDB = new DeviceForDB(null, deviceData.getName(), deviceData.getDescription(),
                deviceData.getDeviceUri(), functionalityForDB, aspectForDB, locationForDB,
                Calendar.getInstance().getTime());
        return deviceForDB;
    }
}
