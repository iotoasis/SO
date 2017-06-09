package com.pineone.icbms.so.interfaces.messagequeue.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pineone.icbms.so.interfaces.database.model.TrackingEntity;
import com.pineone.icbms.so.virtualobject.common.AGenericServiceEntity;

import java.sql.Timestamp;

/**
 * This class have handler properties for MQ.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 5..
 */
abstract public class ACommonForMQ extends AGenericServiceEntity /* AGenericIdNameOwner */ {

    /**
     * simulator Id
     */
    String simulatorId;

    /**
     * trackingEntity data
     */
    TrackingEntity trackingEntity;

    /**
     * message created date
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Timestamp createdDate;

    /**
     * message expire date
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Timestamp expireDate;

    /**
     * priority
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    int priority;

//    /**
//     * stateRegistry
//     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    DefaultStateStore<String> stateRegistry;

    /**
     * description
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String description;


    /**
     * constructor
     */
    public ACommonForMQ() {
    }

    /**
     * constructor
     */
    public ACommonForMQ(String id) {
        this();
        this.id = id;
    }

    /**
     * constructor
     */
    public ACommonForMQ(String id, String name) {
        this(id);
        this.name = name;
    }

    public void setSimulatorId(String simulatorId) {
        this.simulatorId = simulatorId;
    }

    public String getSimulatorId() {
        return this.simulatorId;
    }

    public void setTrackingEntity(TrackingEntity trackingEntity) {
        this.trackingEntity = trackingEntity;
    }

    public TrackingEntity getTrackingEntity() {
        return this.trackingEntity;
    }


//    public String getTrackingEntity() {
//        return trackingEntity;
//    }
//
//    public void setTrackingEntity(String trackingEntity) {
//        this.trackingEntity = trackingEntity;
//    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

//    public DefaultStateStore getStateRegistry() {
//        return stateRegistry;
//    }
//
//    public void setStateRegistry(DefaultStateStore<String> stateRegistry) {
//        this.stateRegistry = stateRegistry;
//    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void addState(String key, String value) {
//        if (stateRegistry == null)
//            stateRegistry = new DefaultStateStore<>();
//        stateRegistry.addValue(key, value);
//    }
//
//    public String removeState(String key) {
//        return stateRegistry.removeValue(key);
//    }
//
//    public String getState(String key) {
//        return stateRegistry.getValue(key);
//    }
//
//    public String updateState(String key, String value) {
//        stateRegistry.removeValue(key);
//        return stateRegistry.addValue(key, value);
//    }

//    @JsonProperty("state")
//    public Map getStateRegistryStore() {
//        if (stateRegistry != null)
//            return stateRegistry.getStore();
//        return null;
//    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append(", priority: ").append(priority);
//        if (stateRegistry != null)
//            sb.append(", stateRegistry: ").append(stateRegistry);
        if (createdDate != null)
            sb.append(", createdDate = ").append(createdDate);
        if (expireDate != null)
            sb.append(", expireDate = ").append(expireDate);
        if (description != null)
            sb.append(", desc = ").append(description);
        return sb.toString();
    }
}
