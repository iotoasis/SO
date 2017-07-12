package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Orchestration Service model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class OrchestrationServiceForDB extends CommonEntity {

    @Setter @Getter
    private String id;

    @Setter @Getter
    private String parent_id;

    @Setter @Getter
    private List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList;

    @Setter @Getter
    private List<VirtualObjectForDB> virtualObjectForDBList;


//    public List<CompositeVirtualObjectForDB> getCompositeVirtualObjectForDBList() {
//        return compositeVirtualObjectForDBList;
//    }
//
//    public void setCompositeVirtualObjectForDBList(List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList) {
//        this.compositeVirtualObjectForDBList = compositeVirtualObjectForDBList;
//    }
//
//    public List<VirtualObjectForDB> getVirtualObjectForDBList() {
//        return virtualObjectForDBList;
//    }
//
//    public void setVirtualObjectForDBList(List<VirtualObjectForDB> virtualObjectForDBList) {
//        this.virtualObjectForDBList = virtualObjectForDBList;
//    }

}

