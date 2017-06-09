package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.controller.inputdata.AspectData;
import com.pineone.icbms.so.interfaces.database.logic.itf.IAspectDAO;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import com.pineone.icbms.so.interfaces.database.repository.AspectRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * context for Aspect.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/aspect")
@ResponseStatus(value = HttpStatus.OK)
public class AspectController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * aspect repository(DAO)
     */
    @Autowired
    private IAspectDAO aspectDAO;

    @Autowired
    private AspectRepository repository;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository AspectRepository
//     */
//    @Autowired
//    public AspectController(AspectRepository repository) {
//        this.repository = repository;
//    }

    /**
     * response for request "/aspect, HTTP-method:POST".<BR/>
     *
     * @param aspect Aspect
     * @return created aspect id
     */

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public AspectForDB createAspect(@RequestBody AspectData aspect) {
        //
        AspectForDB aspectForDB = aspectDAO.createAspect(aspect);
        return aspectForDB;
    }

    /**
     * response for request "/aspect/{id}" .<BR/>
     *
     * @param id aspect id
     * @return aspect
     */
    @RequestMapping(value = "/{id}")
    public AspectForDB getAspect(@RequestParam("id") String id) {
        AspectForDB aspect = repository.findOne(id);
        return aspect;
    }

    /**
     * response for request "/aspect".<BR/>
     *
     * @return aspect list
     */
    @RequestMapping()
    public List<AspectForDB> getAspectList() {
        List<AspectForDB> apsectList = repository.findAll();
        return apsectList;
    }

    /**
     * response for request "/aspect, HTTP-method:PATCH(update)".<BR/>
     *
     * @param aspect Aspect
     * @return updated Aspect id
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public String updateAspect(AspectForDB aspect) {
        //implements...
        return null;
    }

    /**
     * response for request "/aspect/{id}, HTTP-method:DELETE".<BR/>
     *
     * @param id aspect id
     * @return deleted aspect id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteAspect(@RequestParam("id") String id) {
        repository.delete(id);
        //삭제할 aspect 관련 db update 필요.
        return id;
    }

    /**
     * response for request "/aspect/{id}, HTTP-method:POST, 'register'".<BR/>
     *
     * @param id aspect id
     * @return registed aspect id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerAspect(String id) {
        //implements...
        return null;
    }
}
