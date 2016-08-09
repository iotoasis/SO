package com.pineone.icbms.so.contextinformation.entity;

import com.pineone.icbms.so.contextinformation.temp.device.ConceptService;
import com.pineone.icbms.so.contextinformation.temp.device.DeviceObject;

/**
 * Created by melvin on 2016. 7. 29..
 */
public class ContextInformation {
    private String id;
    private String name;
    private DeviceObject deviceObject;
    private int minValue;
    private int maxValue;
    private ConceptService conceptService;

    public static ContextInformation newContextInformation(){
        return new ContextInformation();
    }

    public ContextInformation(){};

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

    public DeviceObject getDeviceObject() {
        return deviceObject;
    }

    public void setDeviceObject(DeviceObject deviceObject) {
        this.deviceObject = deviceObject;
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

    public ConceptService getConceptService() {
        return conceptService;
    }

    public void setConceptService(ConceptService conceptService) {
        this.conceptService = conceptService;
    }
}
