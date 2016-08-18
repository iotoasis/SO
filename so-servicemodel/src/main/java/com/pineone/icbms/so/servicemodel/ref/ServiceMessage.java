package com.pineone.icbms.so.servicemodel.ref;

import com.pineone.icbms.so.domain.entity.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 2016. 8. 16..
 * NOTE: ServiceModel 이름과 DomainList 를 포함한 객체. Queue 에 넣기위해 사용
 */
public class ServiceMessage {
    //

    public ServiceMessage(List<Domain> domainList, String serviceModelName){};

    private List<Domain> domainList = new ArrayList<>();
    private String serviceModelName;

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public String getServiceModelName() {
        return serviceModelName;
    }

    public void setServiceModelName(String serviceModelName) {
        this.serviceModelName = serviceModelName;
    }
}
