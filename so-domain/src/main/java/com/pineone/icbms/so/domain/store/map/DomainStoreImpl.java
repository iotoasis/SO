package com.pineone.icbms.so.domain.store.map;

import com.pineone.icbms.so.domain.entity.Domain;
import com.pineone.icbms.so.domain.ref.Domain_Note;
import com.pineone.icbms.so.domain.store.DomainStore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 17..
 */

public class DomainStoreImpl {
    //
    public List<Domain> retrieveDomainList() {
        //
        List<Domain> domainList = new ArrayList<>();
        domainList.add(new Domain("1", Domain_Note.NAME_CLASSROOM, Domain_Note.URI_CLASSROOM));
        domainList.add(new Domain("2",Domain_Note.NAME_COMPUTERROOM, Domain_Note.URI_COMPUTERROOM));
        domainList.add(new Domain("3",Domain_Note.NAME_DORMITORY, Domain_Note.URI_DORMITORY));
        domainList.add(new Domain("4",Domain_Note.NAME_LABORATORY, Domain_Note.URI_LABORATORY));
        return domainList;
    }
}
