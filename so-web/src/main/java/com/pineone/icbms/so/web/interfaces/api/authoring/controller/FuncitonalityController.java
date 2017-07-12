package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.dao.FunctionalityDao;
import com.pineone.icbms.so.interfaces.database.model.FunctionalityForDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * context for functionality.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/functionality")
@ResponseStatus(value = HttpStatus.OK)
public class FuncitonalityController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
    @Autowired
    private FunctionalityDao dao;

    /**
     * response for request "/functionality, HTTP-method:POST".<BR/>
     * @param functionalityForDB FunctionalityForDB
     * @return created FunctionalityForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public FunctionalityForDB createFunctionality(@RequestBody FunctionalityForDB functionalityForDB) {
        return dao.create(functionalityForDB);
    }

    /**
     * response for request "/functionality/{id}" .<BR/>
     * @param id FunctionalityForDB id
     * @return FunctionalityForDB
     */
    @RequestMapping(value = "/{id}")
    public FunctionalityForDB getFunctionality(@PathVariable String id) {
        return dao.retrieve(id);
    }

    /**
     * response for request "/functionality".<BR/>
     * @return FunctionalityForDB list
     */
    @RequestMapping()
    public List<FunctionalityForDB> getFunctionalityList(@RequestBody FunctionalityForDB functionalityForDB) {
        return dao.retrieve(functionalityForDB);
    }

    /**
     * response for request "/functionality, HTTP-method:PATCH(update)".<BR/>
     * @param functionality FunctionalityForDB
     * @return updated FunctionalityForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public int updateFunctionality(FunctionalityForDB functionality) {
        return dao.update(functionality);
    }

    /**
     * response for request "/functionality/{id}, HTTP-method:DELETE".<BR/>
     * @param id FunctionalityForDB id
     * @return deleted FunctionalityForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteFunctionality(@PathVariable String id) {

        return dao.delete(id);
    }

    /**
     * response for request "/functionality/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id FunctionalityForDB id
     * @return registed FunctionalityForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
//    public String registerFunctionality(String id) {
//        //implements...
//        return null;
//    }
}
