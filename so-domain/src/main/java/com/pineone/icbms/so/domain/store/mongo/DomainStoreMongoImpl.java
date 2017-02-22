package com.pineone.icbms.so.domain.store.mongo;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.logic.DomainLogicImpl;
import com.pineone.icbms.so.domain.store.DomainStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 22..
 * NOTE: Mongo DB를 - Domain 정보 연결
 */
@Repository
public class DomainStoreMongoImpl implements DomainStore {


    public static final Logger logger = LoggerFactory.getLogger(DomainStoreMongoImpl.class);

    @Autowired
    DomainRepository domainRepository;

    //NOTE: 디비에 도메인 데이터 생성
    @Override
    public void createDomain(Domain domain) {
        DomainDataObject domainDataObject = domainToDataObject(domain);
        domainRepository.save(domainDataObject);
    }

    //NOTE: 디비의 도메인 리스트 조회
    @Override
    public List<Domain> retrieveDomainList() {
        List<DomainDataObject> domainDataObjectList = domainRepository.findAll();
        List<Domain> domainList = new ArrayList<>();
        for(DomainDataObject domainDataObject : domainDataObjectList){
            domainList.add(dataObjectToDomain(domainDataObject));
        }
        return domainList;
    }

    @Override
    public Domain read(String id) {
        DomainDataObject domainDataObject = domainRepository.findOne(id);
        return dataObjectToDomain(domainDataObject);
    }

    @Override
    public Domain retrieveDomainDetailByName(String domainName) {
        DomainDataObject domainDataObject = domainRepository.findByName(domainName);
        logger.debug("Domain = " + dataObjectToDomain(domainDataObject));
        return dataObjectToDomain(domainDataObject);
    }

    private DomainDataObject domainToDataObject(Domain domain) {
        if(domain == null) return null;
        return new DomainDataObject(domain.getId(), domain.getName(), domain.getUri());
    }

    private Domain dataObjectToDomain(DomainDataObject domainDataObject){
        if(domainDataObject == null) return null;
        return new Domain(domainDataObject.getId(), domainDataObject.getName(), domainDataObject.getUri());
    }

}
