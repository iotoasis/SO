package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.CommonEntity;
import com.pineone.icbms.so.util.time.DateFormat;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * aspect model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class AspectForDB extends CommonEntity {

    @Getter @Setter
    String uri;
}
