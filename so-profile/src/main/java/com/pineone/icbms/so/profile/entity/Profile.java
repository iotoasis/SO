package com.pineone.icbms.so.profile.entity;


/**
 * Created by melvin on 2016. 8. 11..
 * NOTE: 로직에 사용될 Profile 데이터
 */
public class Profile {


    private String id;
    private String name;
    private String contextModelName;
    private String serviceModelName;
    private String bizContextName;
    private int period;
    private int expirationTime;
    private boolean happenContextModel;
    private String createTime;
    private String modifiedTime;

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

    public String getContextModelName() {
        return contextModelName;
    }

    public void setContextModelName(String contextModelName) {
        this.contextModelName = contextModelName;
    }

    public String getServiceModelName() {
        return serviceModelName;
    }

    public void setServiceModelName(String serviceModelName) {
        this.serviceModelName = serviceModelName;
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

    public Profile(String id, String name, String contextModelName, String serviceModelName, String bizContextName, int period) {
        this.id = id;
        this.name = name;
        this.contextModelName = contextModelName;
        this.serviceModelName = serviceModelName;
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
}
