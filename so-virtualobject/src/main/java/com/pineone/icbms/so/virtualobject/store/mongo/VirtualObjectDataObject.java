package com.pineone.icbms.so.virtualobject.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VirtualObject")
public class VirtualObjectDataObject {
    //
    private String id;
    private String voName;
    private String functionality;
    private String voDescription;
    private String voCreateTime;
    private String voExpiredTime;
    private String aspect;
    private String deviceId;
    private String voCommand;
    private String voLocation;


    public VirtualObjectDataObject() {
    }

    public VirtualObjectDataObject(String id, String voName, String functionality, String voDescription, String voCreateTime, String voExpiredTime, String aspect, String deviceId, String voCommand, String voLocation) {
        this.id = id;
        this.voName = voName;
        this.functionality = functionality;
        this.voDescription = voDescription;
        this.voCreateTime = voCreateTime;
        this.voExpiredTime = voExpiredTime;
        this.aspect = aspect;
        this.deviceId = deviceId;
        this.voCommand = voCommand;
        this.voLocation = voLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getVoDescription() {
        return voDescription;
    }

    public void setVoDescription(String voDescription) {
        this.voDescription = voDescription;
    }

    public String getVoCreateTime() {
        return voCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        this.voCreateTime = voCreateTime;
    }

    public String getVoExpiredTime() {
        return voExpiredTime;
    }

    public void setVoExpiredTime(String voExpiredTime) {
        this.voExpiredTime = voExpiredTime;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVoCommand() {
        return voCommand;
    }

    public void setVoCommand(String voCommand) {
        this.voCommand = voCommand;
    }

    public String getVoLocation() {
        return voLocation;
    }

    public void setVoLocation(String voLocation) {
        this.voLocation = voLocation;
    }

    @Override
    public String toString() {
        return "VirtualObjectDataObject{" +
                "id='" + id + '\'' +
                ", voName='" + voName + '\'' +
                ", functionality='" + functionality + '\'' +
                ", voDescription='" + voDescription + '\'' +
                ", voCreateTime='" + voCreateTime + '\'' +
                ", voExpiredTime='" + voExpiredTime + '\'' +
                ", aspect='" + aspect + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", voCommand='" + voCommand + '\'' +
                ", voLocation='" + voLocation + '\'' +
                '}';
    }
}
