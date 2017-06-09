package com.pineone.icbms.so.interfaces.database.controller.inputdata;

/**
 * Created by melvin on 2017. 4. 21..
 */
public class DeviceData {

    String name;
    String deviceUri;
    String functionalityId;
    String aspectId;
    String locationId;
    String description;

    public DeviceData(String name, String deviceUri, String functionalityId, String aspectId, String locationId, String description) {
        this.name = name;
        this.deviceUri = deviceUri;
        this.functionalityId = functionalityId;
        this.aspectId = aspectId;
        this.locationId = locationId;
        this.description = description;
    }

    public DeviceData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceUri() {
        return deviceUri;
    }

    public void setDeviceUri(String deviceUri) {
        this.deviceUri = deviceUri;
    }

    public String getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(String functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getAspectId() {
        return aspectId;
    }

    public void setAspectId(String aspectId) {
        this.aspectId = aspectId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
