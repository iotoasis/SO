package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * Function model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
//@AllArgsConstructor
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class FunctionalityForDB extends CommonEntity {

    @Setter @Getter
    private String uri;

}
