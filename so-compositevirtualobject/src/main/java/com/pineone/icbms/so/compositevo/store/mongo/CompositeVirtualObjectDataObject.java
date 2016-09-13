package com.pineone.icbms.so.compositevo.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CompositeVirtualObject")
public class CompositeVirtualObjectDataObject {
    //
    private String id;
    private String name;
    private List<String> voList;
    private String voFunctionality;
    private String location;
    private String createTime;
    private String modifiedTime;
    private String description;

    public CompositeVirtualObjectDataObject() {
    }

    public CompositeVirtualObjectDataObject(String id, String name, List<String> voList, String voFunctionality, String location, String createTime, String modifiedTime, String description) {
        this.id = id;
        this.name = name;
        this.voList = voList;
        this.voFunctionality = voFunctionality;
        this.location = location;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.description = description;
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

    public List<String> getVoList() {
        return voList;
    }

    public void setVoList(List<String> voList) {
        this.voList = voList;
    }

    public String getVoFunctionality() {
        return voFunctionality;
    }

    public void setVoFunctionality(String voFunctionality) {
        this.voFunctionality = voFunctionality;
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
}
