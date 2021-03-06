package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * ProfileForDB model for authoring.<BR/>
 * <p>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class ProfileForDB extends CommonEntity {

    @Getter @Setter
    private int enabled;

    @Getter @Setter
    private String contextModelId;

    @Getter @Setter
    private String locationId;

    @Getter @Setter
    private String orchestrationServiceId;

    @Getter @Setter
    private String parameterType;
    
    @Getter @Setter
    private Integer period;

    @Getter @Setter
    private Integer checkRate;

    @Getter @Setter
    private String priority;

    @Getter @Setter
    private String locationUri;
}
