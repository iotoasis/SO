package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.model.ContextModelForDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * context for ProfileForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/cm/*")
public class ContextModelController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * profile repository(DAO)
     */
//    @Autowired
//    private ContextModelDao dao;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository
//     */
//    @Autowired
//    public ContextModelController(ContextModelRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/cm, HTTP-method:POST".<BR/>
     * @param cm ContextModelForMQ
     * @return created ContextModelForMQ id
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createContextModel(ContextModelForDB cm) {
        //implements...
        return null;
    }

    /**
     * response for request "/cm/{id}" .<BR/>
     * @param id ContextModelForMQ id
     * @return ContextModelForMQ
     */
//    @RequestMapping(value = "/{id}")
//    public ContextModelForDB getContextModel(@RequestParam("id") String id) {
//        ContextModelForDB cm = repository.findOne(id);
//        return cm;
//    }

    /**
     * response for request "/cm".<BR/>
     * @return ContextModelForMQ list
     */
//    @RequestMapping()
//    public List<ContextModelForDB> getContextModelList() {
//        List<ContextModelForDB> cmList = repository.findAll();
//        return cmList;
//    }

    /**
     * response for request "/cm, HTTP-method:PATCH(update)".<BR/>
     * @param cm ContextModelForMQ
     * @return updated ContextModelForMQ id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateContextModel(ContextModelForDB cm) {
        //implements...
        return null;
    }

    /**
     * response for request "/cm/{id}, HTTP-method:DELETE".<BR/>
     * @param id ContextModelForMQ id
     * @return deleted ContextModelForMQ id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteContextModel(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/cm/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id ContextModelForMQ id
     * @return registed ContextModelForMQ id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerContextModel(String id) {
        //implements...
        return null;
    }
}
