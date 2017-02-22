package com.pineone.icbms.so.compositevo.pr;

import java.util.List;

public class CompositeVirtualObjectTransFormObject {
    //
    private String id;
    private String name;
    private List<String> voIdList;
    private String location;
    private String createTime;
    private String modifiedTime;
    private String description;
    private String functionality;
    private String aspect;


    public CompositeVirtualObjectTransFormObject() {
    }

    public CompositeVirtualObjectTransFormObject(String id, String name, List<String> voIdList, String location, String createTime, String modifiedTime, String description, String functionality, String aspect) {
        this.id = id;
        this.name = name;
        this.voIdList = voIdList;
        this.location = location;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.description = description;
        this.functionality = functionality;
        this.aspect = aspect;
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

    public List<String> getVoIdList() {
        return voIdList;
    }

    public void setVoIdList(List<String> voIdList) {
        this.voIdList = voIdList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    @Override
    public String toString() {
        return "CompositeVirtualObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", voIdList=" + voIdList +
                ", location='" + location + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", description='" + description + '\'' +
                ", functionality='" + functionality + '\'' +
                ", aspect='" + aspect + '\'' +
                '}';
    }
}
