package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.model.ContextInformationForDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * context for ContextInformationForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/ci/*")
public class ContextInformationController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
//    @Autowired
//    private ContextInformationDao dao;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository ContextInformationRepository
//     */
//    @Autowired
//    public ContextInformationController(ContextInformationRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/ci, HTTP-method:POST".<BR/>
     * @param ci ContextInformationForDB
     * @return created ContextInformationForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createContextInformation(ContextInformationForDB ci) {
        //implements...
        return null;
    }

    /**
     * response for request "/ci/{id}" .<BR/>
     * @param id ContextInformationForDB id
     * @return ContextInformationForDB
     */
//    @RequestMapping(value = "/{id}")
//    public ContextInformationForDB getContextInformation(@RequestParam("id") String id) {
//        ContextInformationForDB ci = repository.findOne(id);
//        return ci;
//    }

    /**
     * response for request "/ci".<BR/>
     * @return ContextInformationForDB list
     */
//    @RequestMapping()
//    public List<ContextInformationForDB> getContextInformationList() {
//        List<ContextInformationForDB> ciList = repository.findAll();
//        return ciList;
//    }

    /**
     * response for request "/ci, HTTP-method:PATCH(update)".<BR/>
     * @param ci ContextInformationForDB
     * @return updated ContextInformationForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateContextInformation(ContextInformationForDB ci) {
        //implements...
        return null;
    }

    /**
     * response for request "/ci/{id}, HTTP-method:DELETE".<BR/>
     * @param id ContextInformationForDB id
     * @return deleted ContextInformationForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteContextInformation(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/ci/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id ContextInformationForDB id
     * @return registed ContextInformationForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerContextInformation(String id) {
        //implements...
        return null;
    }
}
