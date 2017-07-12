package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.model.OrchestrationServiceForDB;

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
@RequestMapping("/os/*")
public class OrchestrationServiceController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
//    @Autowired
//    private OrchestrationServiceDao dao;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository OrchestrationServiceRepository
//     */
//    @Autowired
//    public OrchestrationServiceController(OrchestrationServiceRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/os, HTTP-method:POST".<BR/>
     * @param os OrchestrationServiceForDB
     * @return created OrchestrationServiceForDB id
     */
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public String createOrchestrationService(@RequestBody OrchestrationServiceForDB os) {
//    	repository.save(os);
//        return String.valueOf(os.getId());
//    }

    /**
     * response for request "/os/{id}" .<BR/>
     * @param id OrchestrationServiceForDB id
     * @return OrchestrationServiceForDB
     */
//    @RequestMapping(value = "/{id}")
//    public OrchestrationServiceForDB getOrchestrationService(@RequestParam("id") String id) {
//        OrchestrationServiceForDB os = repository.findOne(id);
//        return os;
//    }

    /**
     * response for request "/os".<BR/>
     * @return OrchestrationServiceForDB list
     */
//    @RequestMapping()
//    public List<OrchestrationServiceForDB> getOrchestrationServiceList() {
//        List<OrchestrationServiceForDB> osList = repository.findAll();
//        return osList;
//    }

    /**
     * response for request "/os, HTTP-method:PATCH(update)".<BR/>
     * @param os OrchestrationServiceForDB
     * @return updated OrchestrationServiceForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public String updateOrchestrationService(OrchestrationServiceForDB os) {
        //implements...
        return null;
    }

    /**
     * response for request "/os/{id}, HTTP-method:DELETE".<BR/>
     * @param id OrchestrationServiceForDB id
     * @return deleted OrchestrationServiceForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteOrchestrationService(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/os/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id OrchestrationServiceForDB id
     * @return registed OrchestrationServiceForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerOrchestrationService(String id) {
        //implements...
        return null;
    }
}
