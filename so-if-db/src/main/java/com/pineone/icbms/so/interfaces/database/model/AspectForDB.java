package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.CommonEntity;
import lombok.*;

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

    @Getter @Setter
    String containerName;
}
