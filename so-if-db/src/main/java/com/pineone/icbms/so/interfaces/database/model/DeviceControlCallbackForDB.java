package com.pineone.icbms.so.interfaces.database.model;

import javax.persistence.*;

/**
 * DeviceControlCallbackForDB model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
@Entity
@Table(name = "device_control_callback")
public class DeviceControlCallbackForDB extends CommonEntity {

    @Id
    @Column(name = "deivce_control_id")
    String id;

    @JoinColumn(table = "orchestration_service", name = "id", nullable = false)
    String parentId;

    @JoinColumn(table = "virtual_object", name = "id", nullable = false)
    String virtualObjectId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getVirtualObjectId() {
        return virtualObjectId;
    }

    public void setVirtualObjectId(String virtualObjectId) {
        this.virtualObjectId = virtualObjectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

