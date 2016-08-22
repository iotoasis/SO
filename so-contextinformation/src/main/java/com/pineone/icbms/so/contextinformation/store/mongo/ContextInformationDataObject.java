package com.pineone.icbms.so.contextinformation.store.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by melvin on 2016. 8. 22..
 * * NOTE: 내부 저장소 에서 사용할 ContextInformation Data
 */

@Document(collection = "context_information")
public class ContextInformationDataObject
{
    //
    private String id;
    private String name;
    private String deviceObjectName;
    private int minValue;
    private int maxValue;
    private String conceptServiceName;

    public ContextInformationDataObject() {
    }

    public ContextInformationDataObject(String id, String name, String deviceObjectName, int minValue, int maxValue, String conceptServiceName) {
        this.id = id;
        this.name = name;
        this.deviceObjectName = deviceObjectName;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.conceptServiceName = conceptServiceName;
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

    public String getDeviceObjectName() {
        return deviceObjectName;
    }

    public void setDeviceObjectName(String deviceObjectName) {
        this.deviceObjectName = deviceObjectName;
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

    public String getConceptServiceName() {
        return conceptServiceName;
    }

    public void setConceptServiceName(String conceptServiceName) {
        this.conceptServiceName = conceptServiceName;
    }
}
