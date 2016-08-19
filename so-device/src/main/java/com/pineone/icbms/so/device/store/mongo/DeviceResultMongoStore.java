package com.pineone.icbms.so.device.store.mongo;

import com.pineone.icbms.so.device.entity.DeviceResult;
import com.pineone.icbms.so.device.store.DeviceResultStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DeviceResultMongoStore implements DeviceResultStore {

    @Autowired
    DeviceResultRepository deviceResultRepository;

    @Override
    public void create(DeviceResult deviceResult) {
        DeviceResultDataObject d = deviceResultToDataObject(deviceResult);
        deviceResultRepository.save(d);
    }

    @Override
    public DeviceResult retrieve(String id) {
        DeviceResultDataObject d = deviceResultRepository.findOne(id);
        return DeviceResultObjectToDeviceResult(d);
    }

    @Override
    public void update(DeviceResult deviceResult) {
        DeviceResultDataObject d = deviceResultToDataObject(deviceResult);
        deviceResultRepository.save(d);
//        deviceResultRepository.insert(d);
    }

    @Override
    public void delete(String id) {
        deviceResultRepository.delete(id);
    }

    private DeviceResultDataObject deviceResultToDataObject(DeviceResult deviceResult){
        if(deviceResult == null) {
            return null;
        }
        return new DeviceResultDataObject(deviceResult.getSendMessage(),deviceResult.getResult1(),deviceResult.getResult2(),deviceResult.getCommandId(),deviceResult.getValue(),deviceResult.getDeviceUrl());
    }

    private DeviceResult DeviceResultObjectToDeviceResult(DeviceResultDataObject deviceResultDataObject){
        if(deviceResultDataObject == null){
            return null;
        }
        return new DeviceResult(deviceResultDataObject.getSendMessage(), deviceResultDataObject.getResult1(), deviceResultDataObject.getResult2(), deviceResultDataObject.getCommandId(), deviceResultDataObject.getValue(), deviceResultDataObject.getDeviceUrl());
    }
}
