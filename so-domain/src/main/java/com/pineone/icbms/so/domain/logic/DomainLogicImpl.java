package com.pineone.icbms.so.domain.logic;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.store.DomainStore;
import com.pineone.icbms.so.domain.store.DomainStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: Domain 관련 로직
 */

public class DomainLogicImpl implements DomainLogic {

    DomainStore domainStore = new DomainStoreImpl();

//    @Autowired
//    DomainStoreImpl domainStore;

    public static DomainLogicImpl newDomainLogic(){
        return new DomainLogicImpl();
    }
    //NOTE : 도메인리스트를 조회
    public List<Domain> retrieveDomainList(){
        // NOTE : SDA 에서 도메인 조회 : List<Domain> domainList = DomainSDAProxy.newDomainProxy().retrieveDomainFromSDA();
        List<Domain> domainList = domainStore.retrieveDomainList();
        return domainList;
    }
}
