package com.pineone.icbms.so.contextmodel.entity;

import java.util.List;

/**
 * Created by melvin on 2016. 7. 20..
 * NOTE: Logic 에서 사용할 contextModel Entity
 */
public class ContextModel {

    private String id;
    private String name;
    private List<String> domainIdList;
    private List<String> contextInformationIdList;
    private String contextType;
    private long createTime;
    private long modifiedTime;
    private String occTime;

    public ContextModel() {
    }

    public ContextModel(String id, List<String> domainIdList, String contextType, String occTime) {
        this.id = id;
        this.domainIdList = domainIdList;
        this.contextType = contextType;
        this.occTime = occTime;
    }

    public ContextModel(String id, String name, List<String> domainIdList, List<String> contextInformationIdList, String contextType) {
        this.id = id;
        this.name = name;
        this.domainIdList = domainIdList;
        this.contextInformationIdList = contextInformationIdList;
        this.contextType = contextType;
    }

    public ContextModel(String id, String name, List<String> domainIdList, List<String> contextInformationIdList, String contextType, long createTime, long modifiedTime) {
        this.id = id;
        this.name = name;
        this.domainIdList = domainIdList;
        this.contextInformationIdList = contextInformationIdList;
        this.contextType = contextType;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getDomainIdList() {
        return domainIdList;
    }

    public void setDomainIdList(List<String> domainIdList) {
        this.domainIdList = domainIdList;
    }

    public List<String> getContextInformationIdList() {
        return contextInformationIdList;
    }

    public void setContextInformationIdList(List<String> contextInformationIdList) {
        this.contextInformationIdList = contextInformationIdList;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getOccTime() {
        return occTime;
    }

    public void setOccTime(String occTime) {
        this.occTime = occTime;
    }

    @Override
    public String toString() {
        return "ContextModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", domainIdList=" + domainIdList +
                ", contextInformationIdList=" + contextInformationIdList +
                ", contextType='" + contextType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", occTime='" + occTime + '\'' +
                '}';
    }
}
