package com.pineone.icbms.so.context.contextmodel.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 7. 25..
 * NOTE : DOMAIN_NOTE 에 수동으로 저장하는 도메인 정보를 이용
 */
public class Domain_SO implements Domain {

    private String name;
    private String uri;

    public static Domain_SO newDomain(){
        Domain_SO domain = new Domain_SO();
        return domain;
    }

    //NOTE : 수동으로 저장된 도메인 정보를 이용하여 도메인 리스트를 리턴
    public List<Domain> retrieveDomainList(){
        List<Domain> domainList = new ArrayList<>();
        Domain domain_ClassRoom = Domain_SO.newDomain();
        domain_ClassRoom.setName(Domain_Note.NAME_CLASSROOM);
        domain_ClassRoom.setUri(Domain_Note.URI_CLASSROOM);
        domainList.add(domain_ClassRoom);
        Domain domain_Dormitory = Domain_SO.newDomain();
        domain_Dormitory.setName(Domain_Note.NAME_DORMITORY);
        domain_Dormitory.setUri(Domain_Note.URI_DORMITORY);
        domainList.add(domain_Dormitory);
        Domain domain_Laboratory = Domain_SO.newDomain();
        domain_Laboratory.setName(Domain_Note.NAME_LABORATORY);
        domain_Laboratory.setUri(Domain_Note.URI_LABORATORY);
        domainList.add(domain_Laboratory);
        Domain domain_ComputerRoom = Domain_SO.newDomain();
        domain_ComputerRoom.setName(Domain_Note.NAME_COMPUTERROOM);
        domain_ComputerRoom.setUri(Domain_Note.URI_COMPUTERROOM);
        domainList.add(domain_ComputerRoom);

        return domainList;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
