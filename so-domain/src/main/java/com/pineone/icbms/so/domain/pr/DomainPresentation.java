package com.pineone.icbms.so.domain.pr;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.logic.DomainLogic;
import com.pineone.icbms.so.domain.ref.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by melvin on 2016. 8. 1..
 */
@Controller
@RequestMapping(value = "/domain")
public class DomainPresentation {
    //
    @Autowired
    DomainLogic domainLogic;

    //NOTE: DomainList 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        List<Domain> domainList = domainLogic.retrieveDomainList();
        return domainList;
    }

    //NOTE: Domain 저작
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerDomainController(@RequestBody Domain domain){
        //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        String resultMessage = domainLogic.registerDomain(domain);
        responseMessage.setMessage(resultMessage);
        return responseMessage;
    }

    //NOTE: 특정 Domain 조회
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Domain retrieveDomain(@PathVariable("id") String id){
        //
        Domain domain = domainLogic.retrieveDomain(id);
        return domain;
    }

    public List<String> retrieveDomainIdList(){
        //
        List<String> domainIdList = domainLogic.retrieveDomainIdList();
        return domainIdList;
    }
}



