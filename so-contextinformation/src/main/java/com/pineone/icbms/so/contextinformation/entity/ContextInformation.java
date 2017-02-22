package com.pineone.icbms.so.contextinformation.entity;

/**
 * Created by melvin on 2016. 7. 29..
 * NOTE: Logic 에서 사용할 ContextInformation Entity
 */
public class ContextInformation {
    //
    private String id;
    private String name;
    private String deviceObjectName;
    private int minValue;
    private int maxValue;
    private String conceptServiceName;
    private String createTime;
    private String modifiedTime;

    public static ContextInformation newContextInformation(){
        return new ContextInformation();
    }

    public ContextInformation(){};

    public ContextInformation(String id, String name, String deviceObjectName, int minValue, int maxValue, String conceptServiceName) {
        this.id = id;
        this.name = name;
        this.deviceObjectName = deviceObjectName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.conceptServiceName = conceptServiceName;
    }

    public ContextInformation(String id, String name, String deviceObjectName, int minValue, int maxValue, String conceptServiceName, String createTime, String modifiedTime) {
        this.id = id;
        this.name = name;
        this.deviceObjectName = deviceObjectName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.conceptServiceName = conceptServiceName;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public ContextInformation(String name){
        this.name = name;
    };

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

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getDeviceObjectName() {
        return deviceObjectName;
    }

    public void setDeviceObjectName(String deviceObjectName) {
        this.deviceObjectName = deviceObjectName;
    }

    public String getConceptServiceName() {
        return conceptServiceName;
    }

    public void setConceptServiceName(String conceptServiceName) {
        this.conceptServiceName = conceptServiceName;
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

    @Override
    public String toString() {
        return "ContextInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deviceObjectName='" + deviceObjectName + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", conceptServiceName='" + conceptServiceName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
