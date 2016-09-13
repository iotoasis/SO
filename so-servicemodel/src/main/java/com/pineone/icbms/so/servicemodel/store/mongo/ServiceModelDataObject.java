package com.pineone.icbms.so.servicemodel.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by melvin on 2016. 8. 23..
 * NOTE: 내부 저장소 에서 사용할 ContextModel Data
 */

@Document(collection = "service_model")
public class ServiceModelDataObject {
    //
    private String id;
    private String name;
    private List<String> serviceIdList;
    private String createTime;
    private String modifiedTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public ServiceModelDataObject() {
    }

    public ServiceModelDataObject(String id, String name, List<String> serviceIdList) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
    }

    public ServiceModelDataObject(String id, String name, List<String> serviceIdList, String createTime, String modifiedTime) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "ServiceModelDataObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceIdList=" + serviceIdList +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
