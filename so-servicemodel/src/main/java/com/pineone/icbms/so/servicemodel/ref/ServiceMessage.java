package com.pineone.icbms.so.servicemodel.ref;

import java.util.ArrayList;
import java.util.List;

/**
 * NOTE: ServiceModel 이름과 DomainList 를 포함한 객체. Queue 에 넣기위해 사용
 */
public class ServiceMessage {
    //
    private List<String> domainIdList = new ArrayList<>();
    private String serviceConceptSerivceId;
    private String operation;

    public ServiceMessage(List<String> domainIdList, String serviceConceptSerivceId, String operation) {
        this.domainIdList = domainIdList;
        this.serviceConceptSerivceId = serviceConceptSerivceId;
        this.operation = operation;
    }

    public ServiceMessage() {
    }

    public List<String> getDomainIdList() {
        return domainIdList;
    }

    public void setDomainIdList(List<String> domainIdList) {
        this.domainIdList = domainIdList;
    }

    public String getServiceConceptSerivceId() {
        return serviceConceptSerivceId;
    }

    public void setServiceConceptSerivceId(String serviceConceptSerivceId) {
        this.serviceConceptSerivceId = serviceConceptSerivceId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
