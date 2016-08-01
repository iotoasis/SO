package com.pineone.icbms.so.context_model.pr;

import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.logic.DomainLogic;
import com.pineone.icbms.so.util.address.AddressStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
    NOTE: Domain 관련 UserInterface
 */
@Controller
public class DomainPresentation {

    //NOTE: SO 에서 접근하는 도메인을 조회하는데 사용
    @RequestMapping(value = AddressStore.RETRIEVE_DOMAIN, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomain(){
        //
        List<Domain> domainList = DomainLogic.newDomainLogic().retrieveDomainList();
        return domainList;
    }
}
