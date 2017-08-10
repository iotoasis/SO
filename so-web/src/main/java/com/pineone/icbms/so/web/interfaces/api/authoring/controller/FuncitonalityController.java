package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.dao.FunctionDao;
import com.pineone.icbms.so.interfaces.database.model.FunctionForDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * context for function.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/function")
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
    private FunctionDao dao;

    /**
     * response for request "/function, HTTP-method:POST".<BR/>
     * @param functionForDB FunctionForDB
     * @return created FunctionForDB id
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public FunctionForDB createFunction(@RequestBody FunctionForDB functionForDB) {
        return dao.create(functionForDB);
    }

    /**
     * response for request "/function/{id}" .<BR/>
     * @param id FunctionForDB id
     * @return FunctionForDB
     */
    @RequestMapping(value = "/{id}")
    public FunctionForDB getFunction(@PathVariable String id) {
        return dao.retrieve(id);
    }

    /**
     * response for request "/function".<BR/>
     * @return FunctionForDB list
     */
    @RequestMapping()
    public List<FunctionForDB> getFunctionList(@RequestBody FunctionForDB functionForDB) {
        return dao.retrieve(functionForDB);
    }

    /**
     * response for request "/function, HTTP-method:PATCH(update)".<BR/>
     * @param function FunctionForDB
     * @return updated FunctionForDB id
     */
    @RequestMapping(method=RequestMethod.PATCH)
    public int updateFunction(FunctionForDB function) {
        return dao.update(function);
    }

    /**
     * response for request "/function/{id}, HTTP-method:DELETE".<BR/>
     * @param id FunctionForDB id
     * @return deleted FunctionForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteFunction(@PathVariable String id) {

        return dao.delete(id);
    }

    /**
     * response for request "/function/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id FunctionForDB id
     * @return registed FunctionForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
//    public String registerFunction(String id) {
//        //implements...
//        return null;
//    }
}
