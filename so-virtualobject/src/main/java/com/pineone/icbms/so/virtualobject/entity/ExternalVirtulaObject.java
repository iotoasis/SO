package com.pineone.icbms.so.virtualobject.entity;

public class ExternalVirtulaObject {

    private String voId;
    private String voName;
    private String functionality;
    private String voDiscription;
    private String voCreateTime;
    private String voExfiredTime;
    private String deviceService;
    private String deviceId;
    private String voCommand;
    private String voLocation;


    public ExternalVirtulaObject() {
    }

    public ExternalVirtulaObject(String voId, String voName, String functionality, String voDiscription, String voCreateTime, String voExfiredTime, String deviceService, String deviceId, String voCommand, String voLocation) {
        this.voId = voId;
        this.voName = voName;
        this.functionality = functionality;
        this.voDiscription = voDiscription;
        this.voCreateTime = voCreateTime;
        this.voExfiredTime = voExfiredTime;
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

    public String getVoDiscription() {
        return voDiscription;
    }

    public void setVoDiscription(String voDiscription) {
        this.voDiscription = voDiscription;
    }

    public String getVoCreateTime() {
        return voCreateTime;
    }

    public void setVoCreateTime(String voCreateTime) {
        this.voCreateTime = voCreateTime;
    }

    public String getVoExfiredTime() {
        return voExfiredTime;
    }

    public void setVoExfiredTime(String voExfiredTime) {
        this.voExfiredTime = voExfiredTime;
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
