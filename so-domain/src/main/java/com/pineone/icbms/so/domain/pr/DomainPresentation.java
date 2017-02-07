package com.pineone.icbms.so.domain.pr;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.logic.DomainLogic;
import com.pineone.icbms.so.domain.ref.ResponseMessage;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(DomainPresentation.class);

    @Autowired
    DomainLogic domainLogic;

    //NOTE: DomainList 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<Domain> domainList = domainLogic.retrieveDomainList();
        return domainList;
    }

    //NOTE: Domain 저작
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseMessage registerDomainController(@RequestBody Domain domain){
        //
        logger.info(LogPrint.inputInfoLogPrint());
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
        logger.info(LogPrint.inputInfoLogPrint());
        Domain domain = domainLogic.retrieveDomain(id);
        return domain;
    }

    //NOTE: 도메인 Id 리스트 조회
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<String> retrieveDomainIdList(){
        //
        logger.info(LogPrint.inputInfoLogPrint());
        List<String> domainIdList = domainLogic.retrieveDomainIdList();
        return domainIdList;
    }

    //NOTE : Domain Name 으로 Domain 조회
    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Domain retrieveDomainByName(@PathVariable("name")String domainName){
        logger.info(LogPrint.inputInfoLogPrint());
        Domain domain = domainLogic.retrieveDomainDetailByName(domainName);
        return domain;
    }
}



