package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.model.FixedDeviceForDB;
import com.pineone.icbms.so.interfaces.database.repository.FixedDeviceRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * context for FixedDeviceForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/fd/*")
public class FixedDeviceController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
    @Autowired
    private FixedDeviceRepository repository;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository FixedDeviceRepository
//     */
//    @Autowired
//    public FixedDeviceController(FixedDeviceRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/fd, HTTP-method:POST".<BR/>
     * @param fd FixedDeviceForDB
     * @return created FixedDeviceForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createFixedDevice(FixedDeviceForDB fd) {
        //implements...
        return null;
    }

    /**
     * response for request "/fd/{id}" .<BR/>
     * @param id FixedDeviceForDB id
     * @return FixedDeviceForDB
     */
    @RequestMapping(value = "/{id}")
    public FixedDeviceForDB getFixedDevice(@RequestParam("id") String id) {
        FixedDeviceForDB fd = repository.findOne(id);
        return fd;
    }

    /**
     * response for request "/fd".<BR/>
     * @return FixedDeviceForDB list
     */
    @RequestMapping()
    public List<FixedDeviceForDB> getFixedDeviceList() {
        List<FixedDeviceForDB> fdList = repository.findAll();
        return fdList;
    }

    /**
     * response for request "/fd, HTTP-method:PATCH(update)".<BR/>
     * @param fd FixedDeviceForDB
     * @return updated FixedDeviceForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateFixedDevice(FixedDeviceForDB fd) {
        //implements...
        return null;
    }

    /**
     * response for request "/fd/{id}, HTTP-method:DELETE".<BR/>
     * @param id FixedDeviceForDB id
     * @return deleted FixedDeviceForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteFixedDevice(@RequestParam("id") String id) {
        repository.delete(id);
        return id;
    }

    /**
     * response for request "/fd/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id FixedDeviceForDB id
     * @return registed FixedDeviceForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerFixedDevice(String id) {
        //implements...
        return null;
    }
}
