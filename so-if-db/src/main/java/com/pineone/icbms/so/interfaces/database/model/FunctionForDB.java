package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Function model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
//@AllArgsConstructor
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class FunctionForDB extends CommonEntity {

    @Setter @Getter
    private String uri;

}
