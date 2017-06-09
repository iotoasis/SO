package com.pineone.icbms.so.interfaces.database.controller.inputdata;

/**
 * Created by melvin on 2017. 3. 29..
 */
public class ProfileData {

    private String name;
    private String contextModelId;
    private String orchestration_service_id;
    private String locationId;
    private boolean enabled;
    private String description;
    private int period;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContextModelId() {
        return contextModelId;
    }

    public void setContextModelId(String contextModelId) {
        this.contextModelId = contextModelId;
    }

    public String getOrchestration_service_id() {
        return orchestration_service_id;
    }

    public void setOrchestration_service_id(String orchestration_service_id) {
        this.orchestration_service_id = orchestration_service_id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProfileData(String name, String contextModelId, String orchestration_service_id, String locationId, boolean enabled, String description
    ,int period) {
        this.name = name;
        this.contextModelId = contextModelId;
        this.orchestration_service_id = orchestration_service_id;
        this.locationId = locationId;
        this.enabled = enabled;
        this.description = description;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public ProfileData() {
    }

}
