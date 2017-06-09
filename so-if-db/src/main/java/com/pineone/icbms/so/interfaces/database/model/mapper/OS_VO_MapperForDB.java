package com.pineone.icbms.so.interfaces.database.model.mapper;

import javax.persistence.*;

/**
 * Created by melvin on 2017. 5. 10..
 */


@Entity
@Table(name = "OS_VO")
public class OS_VO_MapperForDB {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "orchestration_service_id")
    private String orchestrationServiceId;

    @Column(name = "virtual_object_id")
    private String virtualObjectId;

    public OS_VO_MapperForDB(String orchestrationServiceId, String virtualObjectId) {
        this.orchestrationServiceId = orchestrationServiceId;
        this.virtualObjectId = virtualObjectId;
    }

    public OS_VO_MapperForDB() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrchestrationServiceId() {
        return orchestrationServiceId;
    }

    public void setOrchestrationServiceId(String orchestrationServiceId) {
        this.orchestrationServiceId = orchestrationServiceId;
    }

    public String getVirtualObjectId() {
        return virtualObjectId;
    }

    public void setVirtualObjectId(String virtualObjectId) {
        this.virtualObjectId = virtualObjectId;
    }

    @Override
    public String toString() {
        return "OS_VO_MapperForDB{" +
                "id='" + id + '\'' +
                ", orchestrationServiceId='" + orchestrationServiceId + '\'' +
                ", locationId='" + virtualObjectId + '\'' +
                '}';
    }
}
