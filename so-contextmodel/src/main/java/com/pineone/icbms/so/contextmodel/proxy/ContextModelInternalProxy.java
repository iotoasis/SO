package com.pineone.icbms.so.contextmodel.proxy;

import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 18..
 */

@Service
public class ContextModelInternalProxy implements ContextModelInProxy {

//    public static ContextModelInternalProxy newContextModelInternalProxy(){
//        return new ContextModelInternalProxy();
//    }

    @Autowired
    ContextInformationPresentation contextInformationPresentation;

    @Autowired
    DomainPresentation domainPresentation;

    @Override
    public List<String> retrieveContextInformationNameList(){
        //
//        ContextInformationPresentation contextInformationPresentation = new ContextInformationPresentation();
        List<String> contextInformationList = contextInformationPresentation.retrieveContextInformationNameList();
        return contextInformationList;
    }

    @Override
    public List<Domain> retrieveDomainList(){
        //
//        DomainPresentation domainPresentation = new DomainPresentation();
        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        return domainList;
    }

}
