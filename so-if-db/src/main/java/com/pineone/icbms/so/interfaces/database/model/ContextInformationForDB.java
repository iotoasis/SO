package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.CommonEntity;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Context Information model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class ContextInformationForDB extends CommonEntity {

    @Getter @Setter
    private String uri;
}
