package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by melvin on 2017. 4. 21..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class DeviceForDB extends CommonEntity {
    @Getter @Setter
    private String deviceUri;

    @Getter @Setter
    private FunctionForDB function;
    @Getter @Setter
    private AspectForDB aspect;
    @Getter @Setter
    private LocationForDB location;

}
