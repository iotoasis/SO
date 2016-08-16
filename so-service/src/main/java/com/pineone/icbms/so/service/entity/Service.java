package com.pineone.icbms.so.service.entity;

import com.pineone.icbms.so.service.ref.ConceptService;
import com.pineone.icbms.so.service.ref.DeviceObject;
import com.pineone.icbms.so.service.ref.Status;

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
    private DeviceObject deviceObject;
    private ConceptService conceptService;
    private Status status;

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

    public ConceptService getConceptService() {
        return conceptService;
    }

    public void setConceptService(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
