package com.pineone.icbms.so.web.interfaces.api.authoring.controller;

import com.pineone.icbms.so.interfaces.database.dao.VirtualObjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * context for ProfileForDB.<BR/>
 *
 * Created by uni4love on 2017. 1. 13..
 */
@RestController
@RequestMapping("/vo/*")
public class VirtualObjectController {
    /**
     * logger
     */
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    /**
     * repository(DAO)
     */
//    @Autowired
//    private VirtualObjectDao dao;

//    /**
//     * constructor.<BR/>
//     *
//     * @param repository VirtualObjectRepository
//     */
//    @Autowired
//    public VirtualObjectController(VirtualObjectRepository repository) {
//        this.repository = repository;
//        //this.repositoryCustom = repositoryCustom;
//    }

    /**
     * response for request "/vo, HTTP-method:POST".<BR/>
     * @param vo VirtualObjectForDB
     * @return created VirtualObjectForDB id
     */
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.OK)
//    public String createVirtualObject(@RequestBody VirtualObjectForDB vo) {
//    	repository.save(vo);
//    	return String.valueOf(vo.getId());
//    }

    /**
     * response for request "/vo/{id}" .<BR/>
     * @param id VirtualObjectForDB id
     * @return VirtualObjectForDB
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public VirtualObjectForDB getVirtualObject(@PathVariable String id) {
//        VirtualObjectForDB vo = repository.findOne(id);
//        return vo;
//    }

    /**
     * response for request "/vo/{id}" .<BR/>
     * @param function id, aspect id
     * @return VirtualObjectForDB
     */
//    @RequestMapping(value = "/{function}/{aspect}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<VirtualObjectForDB> getByFunctionAndAspect(@PathVariable("function") String function
//            , @PathVariable("aspect") String aspect) {
//        List<VirtualObjectForDB> vo = repository.findByFunctionIdAndAspectId(function, aspect);
//        return vo;
//    }


    /**
     * response for request "/vo".<BR/>
     * @return VirtualObjectForDB list
     */
//    @RequestMapping()
//    public List<VirtualObjectForDB> getVirtualObjectList() {
//        List<VirtualObjectForDB> voList = repository.findAll();
//        return voList;
//    }

    /**
     * response for request "/vo, HTTP-method:PATCH(update)".<BR/>
     * @param vo VirtualObjectForDB
     * @return updated VirtualObjectForDB id
     */
//    @RequestMapping(method=RequestMethod.PATCH)
//    @ResponseStatus(HttpStatus.OK)
//    public String updateVirtualObject(@RequestBody VirtualObjectForDB vo) {
//    	// default transactional
//    	repository.save(vo);
//        return String.valueOf(vo.getId());
//    }

    /**
     * response for request "/vo/{id}, HTTP-method:DELETE".<BR/>
     * @param id VirtualObjectForDB id
     * @return deleted VirtualObjectForDB id
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public String deleteVirtualObject(@RequestParam("id") String id) {
//        repository.delete(id);
//        return id;
//    }

    /**
     * response for request "/vo/{id}, HTTP-method:POST, 'register'".<BR/>
     * @param id VirtualObjectForDB id
     * @return registed VirtualObjectForDB id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String registerVirtualObject(String id) {
        //implements...
        return null;
    }
}
