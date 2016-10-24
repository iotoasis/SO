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
    private String createTime;
    private String modifiedTime;
    private String location;
    private String sessionId;


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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ServiceModelTransFormObject() {
    }

    public ServiceModelTransFormObject(String id, String name, List<String> serviceIdList) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
    }

    public ServiceModelTransFormObject(String id, String name, List<String> serviceIdList, String createTime, String modifiedTime, String location) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.location = location;
    }

    @Override
    public String toString() {
        return "ServiceModelTransFormObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceIdList=" + serviceIdList +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", location='" + location + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
