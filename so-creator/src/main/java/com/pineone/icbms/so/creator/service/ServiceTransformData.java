package com.pineone.icbms.so.creator.service;

import java.util.List;

/**
 * Created by melvin on 2016. 10. 6..
 */

public class ServiceTransformData {

    private String name;
    private List<ServiceUnit> serviceUnitList;
    private String filterTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceUnit> getServiceUnitList() {
        return serviceUnitList;
    }

    public void setServiceUnitList(List<ServiceUnit> serviceUnitList) {
        this.serviceUnitList = serviceUnitList;
    }

    public String getFilterTime() {
        return filterTime;
    }

    public void setFilterTime(String filterTime) {
        this.filterTime = filterTime;
    }

    @Override
    public String toString() {
        return "ServiceTransformData{" +
                "name='" + name + '\'' +
                ", serviceUnitList=" + serviceUnitList.toString() +
                ", filterTime='" + filterTime + '\'' +
                '}';
    }
}
