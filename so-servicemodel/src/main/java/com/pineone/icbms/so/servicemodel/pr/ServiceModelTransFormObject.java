package com.pineone.icbms.so.servicemodel.pr;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 23..
 * NOTE: 외부에 노출될 ServiceModel Data - Presentation, Controller 에서 사용
 */
public class ServiceModelTransFormObject {
    //
    private String id;
    private String name;
    private List<String> serviceIdList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServiceIdList() {
        return serviceIdList;
    }

    public void setServiceIdList(List<String> serviceIdList) {
        this.serviceIdList = serviceIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ServiceModelTransFormObject() {
    }

    public ServiceModelTransFormObject(String id, String name, List<String> serviceIdList) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
    }
}
