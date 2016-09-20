package com.pineone.icbms.so.contextmodel.proxy;

import com.pineone.icbms.so.contextinformation.pr.ContextInformationPresentation;
import com.pineone.icbms.so.contextmodel.pr.ContextModelPresentation;
import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.pr.DomainPresentation;
import com.pineone.icbms.so.util.logprint.LogPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 18..
 */

@Service
public class ContextModelInternalProxy implements ContextModelInProxy {

    public static final Logger logger = LoggerFactory.getLogger(ContextModelInternalProxy.class);

//    public static ContextModelInternalProxy newContextModelInternalProxy(){
//        return new ContextModelInternalProxy();
//    }

    @Autowired
    ContextInformationPresentation contextInformationPresentation;

    @Autowired
    DomainPresentation domainPresentation;

    //NOTE: CI 이름 리스트 조회
    @Override
    public List<String> retrieveContextInformationNameList(){
        //
//        ContextInformationPresentation contextInformationPresentation = new ContextInformationPresentation();
        logger.info(LogPrint.outputInfoLogPrint());
        List<String> contextInformationList = contextInformationPresentation.retrieveContextInformationNameList();
        return contextInformationList;
    }

    //NOTE: 도메인 리스트 조회
    @Override
    public List<Domain> retrieveDomainList(){
        //
//        DomainPresentation domainPresentation = new DomainPresentation();
        logger.info(LogPrint.outputInfoLogPrint());
        List<Domain> domainList = domainPresentation.retrieveDomainListController();
        return domainList;
    }

}
