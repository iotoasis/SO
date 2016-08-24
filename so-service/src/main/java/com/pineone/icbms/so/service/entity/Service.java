package com.pineone.icbms.so.service.entity;

/**
 * Created by melvin on 2016. 8. 5..
 */
public class Service {

    public Service(){};

    public Service(String name){
        this.name = name;
    }
    private String id;
    private String name;
    private String deviceObjectId;
    private String conceptServiceId;
    private String status;
    private String createTime;
    private String modifiedTime;

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

    public String getDeviceObjectId() {
        return deviceObjectId;
    }

    public void setDeviceObjectId(String deviceObjectId) {
        this.deviceObjectId = deviceObjectId;
    }

    public String getConceptServiceId() {
        return conceptServiceId;
    }

    public void setConceptServiceId(String conceptServiceId) {
        this.conceptServiceId = conceptServiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Service(String id, String name, String deviceObjectId, String conceptServiceId, String status) {

        this.id = id;
        this.name = name;
        this.deviceObjectId = deviceObjectId;
        this.conceptServiceId = conceptServiceId;
        this.status = status;
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

    public Service(String id, String name, String deviceObjectId, String conceptServiceId, String status, String createTime, String modifiedTime) {
        this.id = id;
        this.name = name;
        this.deviceObjectId = deviceObjectId;
        this.conceptServiceId = conceptServiceId;
        this.status = status;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }


}
