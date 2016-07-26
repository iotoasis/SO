package com.pineone.icbms.so.context.contextmodel.pr;

import com.pineone.icbms.so.context.contextmodel.domain.Domain;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SDA;
import com.pineone.icbms.so.context.contextmodel.domain.Domain_SO;
import com.pineone.icbms.so.context.util.address.AddressStore;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
    NOTE: Domain_SDA 관련 UserInterface
 */
@Controller
public class UserController_Domain {

    //NOTE: DomainList 조회 -> DomainList 리턴
    @RequestMapping(value = AddressStore.RETRIEVE_DOMAIN_SDA, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveSDADomainListController() {
        //
        Domain domainUsingSDA = Domain_SDA.newDomain();
        List<Domain> domainUsingSDAList = domainUsingSDA.retrieveDomainList();
        return domainUsingSDAList;
    }

    //NOTE: 수동으로 저장하는 Domain_SO 정보에 접근할 때 사용
    @RequestMapping(value = AddressStore.RETRIEVE_DOMAIN, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Domain> retrieveDomain(){
        //
        Domain domain = Domain_SO.newDomain();
        List<Domain> domainList = domain.retrieveDomainList();
        return domainList;
    }
}
