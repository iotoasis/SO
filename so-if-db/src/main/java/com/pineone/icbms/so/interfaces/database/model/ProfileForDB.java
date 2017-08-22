package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

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
    private String contextModelId;

    @Getter @Setter
    private String orchestrationServiceId;

    @Getter @Setter
    private String locationUri;

    @Getter @Setter
    private int period;

    @Getter @Setter
    private int enabled;


}
