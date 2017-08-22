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
    private String parentId;

    @Setter @Getter
    private List<CompositeVirtualObjectForDB> compositeVirtualObjectForDBList;

    @Setter @Getter
    private List<VirtualObjectForDB> virtualObjectForDBList;

    @Setter @Getter
    private List<String> compositeVirtualObjectIds;

    @Setter @Getter
    private List<String> virtualObjectIds;

}

