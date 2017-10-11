package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * Context Model model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class ContextModelForDB extends CommonEntity {

    @Getter @Setter
    private String createdId;

    @Getter @Setter
    private String modifiedId;

}
