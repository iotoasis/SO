package com.pineone.icbms.so.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by existmaster on 2016. 8. 9..
 */
@Controller
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String welcome(){
        return "Welcome";
    }
}
