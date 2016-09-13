package com.pineone.icbms.so.profile.entity;


/**
 * Created by melvin on 2016. 8. 11..
 * NOTE: 로직에 사용될 Profile 데이터
 */
public class Profile {


    private String id;
    private String name;
    private String contextModelId;
    private String serviceModelId;
    private String bizContextName;
    private int period;
    private int expirationTime;
    private boolean happenContextModel;
    private String createTime;
    private String modifiedTime;

    public Profile(String id) {
        this.id = id;
    }

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContextModelId() {
        return contextModelId;
    }

    public void setContextModelId(String contextModelId) {
        this.contextModelId = contextModelId;
    }

    public String getServiceModelId() {
        return serviceModelId;
    }

    public void setServiceModelId(String serviceModelId) {
        this.serviceModelId = serviceModelId;
    }

    public boolean isHappenContextModel() {
        return happenContextModel;
    }

    public void setHappenContextModel(boolean happenContextModel) {
        this.happenContextModel = happenContextModel;
    }

    public String getBizContextName() {
        return bizContextName;
    }

    public void setBizContextName(String bizContextName) {
        this.bizContextName = bizContextName;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Profile(String id, String name, String contextModelId, String serviceModelId, String bizContextName, int period) {
        this.id = id;
        this.name = name;
        this.contextModelId = contextModelId;
        this.serviceModelId = serviceModelId;
        this.bizContextName = bizContextName;
        this.period = period;
    }

    public int getPeriod() {

        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contextModelId='" + contextModelId + '\'' +
                ", serviceModelId='" + serviceModelId + '\'' +
                ", bizContextName='" + bizContextName + '\'' +
                ", period=" + period +
                ", expirationTime=" + expirationTime +
                ", happenContextModel=" + happenContextModel +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
