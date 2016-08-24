package com.pineone.icbms.so.virtualobject.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VirtualObject")
public class VirtualObjectDataObject {
    //
    private String voId;
    private String voName;
    private String functionality;
    private String voDescription;
    private String voCreateTime;
    private String voExpiredTime;
    private String deviceService;
    private String deviceId;
    private String voCommand;
    private String voLocation;


    public VirtualObjectDataObject() {
    }

    public VirtualObjectDataObject(String voId, String voName, String functionality, String voDescription, String voCreateTime, String voExpiredTime, String deviceService, String deviceId, String voCommand, String voLocation) {
        this.voId = voId;
        this.voName = voName;
        this.functionality = functionality;
        this.voDescription = voDescription;
        this.voCreateTime = voCreateTime;
        this.voExpiredTime = voExpiredTime;
        this.deviceService = deviceService;
        this.deviceId = deviceId;
        this.voCommand = voCommand;
        this.voLocation = voLocation;
    }

    public String getVoId() {
        return voId;
    }

    public void setVoId(String voId) {
        this.voId = voId;
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

    public String getDeviceService() {
        return deviceService;
    }

    public void setDeviceService(String deviceService) {
        this.deviceService = deviceService;
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
}
