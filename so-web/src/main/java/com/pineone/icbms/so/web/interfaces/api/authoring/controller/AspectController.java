package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.dao.AspectDao;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private AspectDao aspectDAO;

    /**
     * response for request "/aspect, HTTP-method:POST".<BR/>
     *
     * @param aspect Aspect
     * @return created aspect id
     */

    @RequestMapping(method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AspectForDB createAspect(@RequestBody AspectForDB aspect) {
        //
        AspectForDB aspectForDB = aspectDAO.create(aspect);
        return aspectForDB;
    }

    /**
     * response for request "/aspect/{id}" .<BR/>
     *
     * @param id aspect id
     * @return aspect
     */
    @RequestMapping(value = "/{id}")
    public AspectForDB getAspect(@PathVariable String id) {
        AspectForDB aspect = aspectDAO.retrieve(id);
        return aspect;
    }

    /**
     * response for request "/aspect".<BR/>
     *
     * @return aspect list
     */
    @RequestMapping()
    public List<AspectForDB> getAspectList() {
        List<AspectForDB> apsectList = aspectDAO.retrieve(new AspectForDB());
        return apsectList;
    }

    /**
     * response for request "/aspect, HTTP-method:PATCH(update)".<BR/>
     *
     * @param aspect Aspect
     * @return updated Aspect id
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public int updateAspect(@RequestBody AspectForDB aspect) {
        return aspectDAO.update(aspect);
    }

    /**
     * response for request "/aspect/{id}, HTTP-method:DELETE".<BR/>
     *
     * @param id aspect id
     * @return deleted aspect id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteAspect(@PathVariable("id") String id) {
        int cnt = aspectDAO.delete(id);
        //삭제할 aspect 관련 db update 필요.
        return cnt;
    }
}
