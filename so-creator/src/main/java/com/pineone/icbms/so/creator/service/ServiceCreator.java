package com.pineone.icbms.so.creator.service;

import com.pineone.icbms.so.service.pr.ServicePresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by melvin on 2016. 10. 5..
 * NOTE :   저작시 필요한 내용들을 Restful 을 이용해서 외부에 노출
 */

@RestController
@RequestMapping(value = "/service")
@ResponseStatus(value = HttpStatus.OK)
public class ServiceCreator {

    @Autowired
    ServicePresentation servicePresentation;
}
