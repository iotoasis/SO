package com.pineone.icbms.so.profile.entity;

import com.pineone.icbms.so.profile.ref.ControlMessage;

/**
 * Created by melvin on 2016. 8. 11..
 */
public class Profile {

    String name;
    String id;
    String contextModelName;
    String serviceModelName;
    String bizContextName;
    int period;
    int expirationTime;
    boolean happenContextModel;

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

    public int getPeriod() {

        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
