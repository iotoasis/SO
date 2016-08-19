package com.pineone.icbms.so.domain.pr;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.logic.DomainLogic;
import com.pineone.icbms.so.domain.logic.DomainLogicImpl;
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
    DomainLogic domainLogic = DomainLogicImpl.newDomainLogic();
//    @Autowired
//    DomainLogic domainLogic;

    //NOTE: DomainList 조회
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomainListController(){
        //
        List<Domain> domainList = domainLogic.retrieveDomainList();
        return domainList;
    }
}



