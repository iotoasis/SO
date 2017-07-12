package com.pineone.icbms.so.web.interfaces.api.devicecontrol.controller;

import com.pineone.icbms.so.interfaces.database.model.DeviceControlCallbackForDB;
//import com.pineone.icbms.so.interfaces.database.repository.DeviceControlCallbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * result callback for Device control.<BR/>
 *
 * Created by uni4love on 2017. 1. 16..
 */
@RestController
@RequestMapping("/dcc/*")
public class DeviceControlCallbackController {
    /**
     * repository(DAO)
     */
//    private DeviceControlCallbackRepository repository;

    /**
     * constructor.<BR/>
     *
     * @param repository DeviceControlCallbackRepository
     */
//    @Autowired
//    public DeviceControlCallbackController(DeviceControlCallbackRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/dcc, HTTP-method:POST".<BR/>
     * @param dcc DeviceControlCallbackForDB
     * @return created DeviceControlCallbackForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createDeviceControlCallback(DeviceControlCallbackForDB dcc) {
        //implements...
        return null;
    }

    /**
     * response for request "/dcc/{id}" .<BR/>
     * @param id DeviceControlCallbackForDB id
     * @return DeviceControlCallbackForDB
     */
//    @RequestMapping(value = "/{id}")
//    public DeviceControlCallbackForDB getDeviceControlCallback(@RequestParam("id") String id) {
//        DeviceControlCallbackForDB dcc = repository.findOne(id);
//        return dcc;
//    }

    /**
     * response for request "/dcc".<BR/>
     * @return DeviceControlCallbackForDB list
     */
//    @RequestMapping()
//    public List<DeviceControlCallbackForDB> getDeviceControlCallbackList() {
//        List<DeviceControlCallbackForDB> dccList = repository.findAll();
//        return dccList;
//    }

    /**
     * response for request "/dcc, HTTP-method:PATCH(update)".<BR/>
     * @param dcc DeviceControlCallbackForDB
     * @return updated DeviceControlCallbackForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateDeviceControlCallback(DeviceControlCallbackForDB dcc) {
        //implements...
        return null;
    }

    /**
     * response for request "/dcc/{id}, HTTP-method:DELETE".<BR/>
     * @param id DeviceControlCallbackForDB id
     * @return deleted DeviceControlCallbackForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteDeviceControlCallback(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }
}
