package com.pineone.icbms.so.context_model.logic;

import com.pineone.icbms.so.context_model.entity.Domain;
import com.pineone.icbms.so.context_model.ref.Domain_Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 1..
 * NOTE: Domain 관련 로직
 */
public class DomainLogicImpl implements DomainLogic {

    public static DomainLogicImpl newDomainLogic(){
        return new DomainLogicImpl();
    }

    //NOTE : 도메인리스트를 조회
    public List<Domain> retrieveDomainList(){

        // NOTE : SDA 에서 도메인 조회 : List<Domain> domainList = DomainSDAProxy.newDomainProxy().retrieveDomainFromSDA();

        List<Domain> domainList = new ArrayList<>();
        domainList.add(new Domain(Domain_Note.NAME_CLASSROOM, Domain_Note.URI_CLASSROOM));
        domainList.add(new Domain(Domain_Note.NAME_COMPUTERROOM, Domain_Note.URI_COMPUTERROOM));
        domainList.add(new Domain(Domain_Note.NAME_DORMITORY, Domain_Note.URI_DORMITORY));
        domainList.add(new Domain(Domain_Note.NAME_LABORATORY, Domain_Note.URI_LABORATORY));
        return domainList;
    }
}
