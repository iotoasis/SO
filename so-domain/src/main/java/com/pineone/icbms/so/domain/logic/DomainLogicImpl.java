package com.pineone.icbms.so.domain.logic;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.ref.ResponseMessage;
import com.pineone.icbms.so.domain.store.DomainStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: Domain 관련 로직
 */

@Service
public class DomainLogicImpl implements DomainLogic {
    //
    @Autowired
    DomainStore domainStore;
//    public static DomainLogicImpl newDomainLogic(){
//        return new DomainLogicImpl();
//    }
    //NOTE : 도메인리스트를 조회
    public List<Domain> retrieveDomainList(){
        // NOTE : SDA 에서 도메인 조회 : List<Domain> domainList = DomainSDAProxy.newDomainProxy().retrieveDomainFromSDA();
        List<Domain> domainList = domainStore.retrieveDomainList();
        return domainList;
    }

    //NOTE: 도메인 등록 로직
    @Override
    public String registerDomain(Domain domain) {
        //
        ResponseMessage responseMessage = ResponseMessage.newResponseMessage();
        domainStore.createDomain(domain);
        String domainResult = responseMessage.domainResultMessage(domain);
        return domainResult;
    }

    //NOTE: 도메인 조회
    @Override
    public Domain retrieveDomain(String id) {
        Domain domain = domainStore.read(id);
        return domain;
    }

    //NOTE : 도메인 아이디 리스트 조회
    @Override
    public List<String> retrieveDomainIdList() {
        List<Domain> domainList = domainStore.retrieveDomainList();
        List<String> domainIdList = new ArrayList<>();
        for(Domain domain : domainList){
            domainIdList.add(domain.getId());
        }
        return domainIdList;
    }
}
