package com.pineone.icbms.so.webmvc.controller;

import com.pineone.icbms.so.interfaces.database.dao.AspectDao;
import com.pineone.icbms.so.interfaces.database.model.AspectForDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * context for Aspect.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
@Controller
@RequestMapping(value = "/ui/aspect")
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

//    /**
//     * response for request "/aspect, HTTP-method:POST".<BR/>
//     *
//     * @param aspect Aspect
//     * @return created aspect id
//     */
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public AspectForDB createAspect(@ModelAttribute("aspectForm") AspectForDB aspect) {
        //
        AspectForDB aspectForDB = aspectDAO.create(aspect);
        return aspectForDB;
    }

    /**
     * response for request "/aspect/id=?" .<BR/>
     *
     * @param id aspect id
     * @return aspect
     */
    @RequestMapping(value = "/")
    public String getAspect(@RequestParam("id") String id, ModelMap model) {
        AspectForDB apsect = aspectDAO.retrieve(id);
        model.addAttribute("apsect", apsect);
        return "aspect/aspect";
    }

    /**
     * response for request "/aspectList".<BR/>
     *
     * @return aspect list
     */
    @RequestMapping("/list")
    public String getAspectList(ModelMap model) {
        List<AspectForDB> apsectList = aspectDAO.retrieve(new AspectForDB());
        model.addAttribute("apsectList", apsectList);
        return "aspect/aspect";
    }
//
//    /**
//     * response for request "/aspect, HTTP-method:PATCH(update)".<BR/>
//     *
//     * @param aspect Aspect
//     * @return updated Aspect id
//     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AspectForDB updateAspect(@ModelAttribute("aspectForm") AspectForDB aspect) {
        return aspectDAO.update(aspect);
    }

//    /**
//     * response for request "/aspect/{id}, HTTP-method:DELETE".<BR/>
//     *
//     * @param id aspect id
//     * @return deleted aspect id
//     */
    @RequestMapping(value = "/delete")
    public int deleteAspect(@RequestParam("id") String id, ModelMap model) {
        int cnt = aspectDAO.delete(id);
        //삭제할 aspect 관련 db update 필요.
        model.addAttribute("deleteCounter", cnt);
        return cnt;
    }

//    @RequestMapping("/ui/aspect")
//    public String welcome(Map<String, Object> model) {
//        model.put("message", "hi");
//        return "welcome";
//    }
}
