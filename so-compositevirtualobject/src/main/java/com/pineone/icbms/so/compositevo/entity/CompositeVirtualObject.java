package com.pineone.icbms.so.compositevo.entity;

import java.util.List;

public class CompositeVirtualObject {
    //
    /**
     * Composite VirtualObject의 식별자.
     * format : cvo-(cvo제어식별자)-(제어서비스)
     * ex) cvo-airconditioner02001-control
     */
    private String id;

    /**
     * Composite VirtualObject의 이름
     * format : free
     * ex) airconditioner02001 device CVO
     */
    private String name;

    /**
     * Composite VirtualObject가 제어 가능한 VirtualObject의 ID List
     * format : vo의 ID list
     * ex) "vo-airconditioner02-001-power-control", "vo-airconditioner02-001-temp-control"
     */
    private List<String> voIdList;

    /**
     * Composite VirtualObject의 서비스 위치
     * format : free
     * ex) ITBT_606_001
     */
    private String location;

    /**
     * Composite VirtualObject의 생성 시간
     * format : yyyymmddhhmm
     * ex) 201608250930
     */
    private String createTime;

    /**
     * Composite VirtualObject의 수정 시간
     * format : yyyymmddhhmm
     * ex) 201608250930
     */
    private String modifiedTime;

    /**
     * Composite VirtualObject의 설명
     * format : free
     * ex) airconditioner device cvo
     */
    private String description;

    /**
     * Composite VirtualObject의 기능
     * format : http://www.iotoasis.org/ontology/"functionality"
     * ex) http://www.iotoasis.org/ontology/SwitchFunctionality
     */
    private String functionality;

    /**
     * Composite VirtualObject의 관점.
     * format : http://www.iotoasis.org/ontology/"aspect"
     * ex) http://www.iotoasis.org/ontology/luminosity-aspect
     */
    private String aspect;


    public CompositeVirtualObject() {
    }

    public CompositeVirtualObject(String id, String name, List<String> voIdList, String location, String createTime, String modifiedTime, String description, String functionality, String aspect) {
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
