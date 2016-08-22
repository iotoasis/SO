package com.pineone.icbms.so.domain.store;


import com.pineone.icbms.so.domain.entity.Domain;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 2..
 */
public interface DomainStore {
    //
    void createDomain(Domain domain);
    List<Domain> retrieveDomainList();
    Domain read(String id);


}
