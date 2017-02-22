package com.pineone.icbms.so.servicemodel.entity;


import java.util.List;

/**
 * NOTE: 복수의 서비스를 포함하고 있는 서비스 모델 객체
 */
public class ServiceModel {
    /**
     * ServiceModel 식별자
     * format : sm-(free)
     * ex : 'sm-electric-waste-classroom'
     */
    private String id;

    /**
     * ServiceModel 이름
     * format : free
     * ex : '불필요한 디바이스 전력낭비 체크 서비스 모델'
     */
    private String name;

    /**
     * ServiceModel 이 포함하고 있는 Service 들의 식별자 리스트
     */
    private List<String> serviceIdList;

    /**
     * ServiceModel 생성 시간
     * format : yyyymmddhhmm
     * ex : '201608250930'
     */
    private long createTime;

    /**
     * ServiceModel 변경 시간
     * format : yyyymmddhhmm
     * ex : '201608250930'
     */
    private long modifiedTime;

    /**
     * ServiceModel 실행 위치
     * format : string
     * ex : classroom001
     */
    private String location;

    /**
     * ServiceModel 설명
     * format : string
     * ex : 강의조성 실행 서비스 모델
     */
    private String description;

    public ServiceModel() {
    }

    public ServiceModel(String id, String name, List<String> serviceIdList, long createTime, long modifiedTime, String location, String description) {
        this.id = id;
        this.name = name;
        this.serviceIdList = serviceIdList;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServiceIdList() {
        return serviceIdList;
    }

    public void setServiceIdList(List<String> serviceIdList) {
        this.serviceIdList = serviceIdList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceIdList=" + serviceIdList +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
