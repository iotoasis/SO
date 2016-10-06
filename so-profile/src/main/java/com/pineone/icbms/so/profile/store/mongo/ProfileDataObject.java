package com.pineone.icbms.so.profile.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by melvin on 2016. 8. 23..
 * NOTE: 내부 저장소 에서 사용할 Profile Data
 */

@Document(collection = "profile")
public class ProfileDataObject {

    private String id;
    private String name;
    private String contextModelId;
    private String serviceModelId;
    private String bizContextName;
    private String priority;
    private int period;
    private int expirationTime;
    private boolean happenContextModel;
    private String createTime;
    private String modifiedTime;

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

    public int getPeriod() {

        return period;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public ProfileDataObject() {
    }

    public ProfileDataObject(String id, String name, String contextModelId, String serviceModelId, String bizContextName, int period) {
        this.id = id;
        this.name = name;
        this.contextModelId = contextModelId;
        this.serviceModelId = serviceModelId;
        this.bizContextName = bizContextName;
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
