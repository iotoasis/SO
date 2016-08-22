package com.pineone.icbms.so.domain.store.mongo;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.store.DomainStore;
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

    @Autowired
    DomainRepository domainRepository;

    @Override
    public void createDomain(Domain domain) {
        DomainDataObject domainDataObject = domainToDataObject(domain);
        domainRepository.save(domainDataObject);
    }

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

    private DomainDataObject domainToDataObject(Domain domain) {
        if(domain == null) return null;
        return new DomainDataObject(domain.getId(), domain.getName(), domain.getUri());
    }

    private Domain dataObjectToDomain(DomainDataObject domainDataObject){
        if(domainDataObject == null) return null;
        return new Domain(domainDataObject.getId(), domainDataObject.getName(), domainDataObject.getUri());
    }

}
