package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.CommonEntity;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Composite Virtual Object model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class CompositeVirtualObjectForDB extends CommonEntity {

    @Getter @Setter
    private String cvoType;

    @Getter @Setter
    private String deviceClassTypeId;
    
    @Getter @Setter
    private String deviceId;
    
    @Getter @Setter
    private List<VirtualObjectForDB> virtualObjectForDBList;

}
