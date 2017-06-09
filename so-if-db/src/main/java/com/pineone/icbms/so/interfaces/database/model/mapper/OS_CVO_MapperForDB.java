package com.pineone.icbms.so.interfaces.database.model.mapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by melvin on 2017. 5. 15..
 */

@Entity
@Table(name = "OS_CVO")
public class OS_CVO_MapperForDB {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "orchestration_service_id")
    private String orchestrationServiceId;

    @Column(name = "composite_virtual_object_id")
    private String compositeVirtualObjectId;

    public OS_CVO_MapperForDB(String orchestrationServiceId, String compositeVirtualObjectId) {
        this.orchestrationServiceId = orchestrationServiceId;
        this.compositeVirtualObjectId = compositeVirtualObjectId;
    }

    public OS_CVO_MapperForDB() {
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

    public String getCompositeVirtualObjectId() {
        return compositeVirtualObjectId;
    }

    public void setCompositeVirtualObjectId(String compositeVirtualObjectId) {
        this.compositeVirtualObjectId = compositeVirtualObjectId;
    }

    @Override
    public String toString() {
        return "OS_CVO_MapperForDB{" +
                "id='" + id + '\'' +
                ", orchestrationServiceId='" + orchestrationServiceId + '\'' +
                ", compositeVirtualObjectId='" + compositeVirtualObjectId + '\'' +
                '}';
    }
}
