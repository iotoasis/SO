package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.dao.DeviceDao;
import com.pineone.icbms.so.interfaces.database.model.DeviceForDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * context for DeviceForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/device")
@ResponseStatus(value = HttpStatus.OK)
public class DeviceController {
    /**
     * logger
     */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * repository(DAO)
     */
    @Autowired
    private DeviceDao deviceDao;

    /**
     * response for request "/fd, HTTP-method:POST".<BR/>
     * @param device DeviceForDB
     * @return created DeviceForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    public DeviceForDB createDevice(@RequestBody DeviceForDB device) {
        //implements...
        DeviceForDB deviceForDB = deviceDao.create(device);
        return deviceForDB;
    }

    /**
     * response for request "/fd/{id}" .<BR/>
     * @param id DeviceForDB id
     * @return DeviceForDB
     */
//    @RequestMapping(value = "/{id}")
//    public DeviceForDB getDevice(@RequestParam("id") String id) {
//        DeviceForDB fd = repository.findOne(id);
//        return fd;
//    }

    /**
     * response for request "/fd".<BR/>
     * @return DeviceForDB list
     */
    @RequestMapping()
    public List<DeviceForDB> getAllDeviceList() {
        List<DeviceForDB> list = deviceDao.retrieve();
        return list;
    }

    /**
     * response for request "/id".<BR/>
     * @return DeviceForDB
     */
    @RequestMapping(value="/{id}")
    public DeviceForDB getDevice(@PathVariable String id) {
        return deviceDao.retrieve(id);
    }

    /**
     * response for request "/fd, HTTP-method:PATCH(update)".<BR/>
     * @param fd DeviceForDB
     * @return updated DeviceForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateDevice(DeviceForDB fd) {
        //implements...
        return null;
    }

    /**
     * response for request "/fd/{id}, HTTP-method:DELETE".<BR/>
     * @param id DeviceForDB id
     * @return deleted DeviceForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteDevice(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/fd/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id DeviceForDB id
     * @return registed DeviceForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerDevice(String id) {
        //implements...
        return null;
    }
}
