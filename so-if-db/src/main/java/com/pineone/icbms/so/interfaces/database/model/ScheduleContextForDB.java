package com.pineone.icbms.so.interfaces.database.model;

import lombok.*;

/**
 * ScheduleContextForDB model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@ToString
public class ScheduleContextForDB extends CommonEntity {

    @Getter @Setter
    String schedule;

}
