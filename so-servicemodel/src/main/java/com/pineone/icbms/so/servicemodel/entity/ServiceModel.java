package com.pineone.icbms.so.servicemodel.entity;


import java.util.List;

/**
 * Created by melvin on 2016. 8. 9..
 * NOTE: 복수의 서비스를 포함하고 있는 서비스 모델 객체
 */
public class ServiceModel {
    //
    private String name;
    private List<String> serviceNameList;
    private String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServiceNameList() {
        return serviceNameList;
    }

    public void setServiceNameList(List<String> serviceNameList) {
        this.serviceNameList = serviceNameList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
