package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * user_info model for authoring.<BR/>
 * Created by jonghee on 2017. 1. 16..
 */
@JsonPropertyOrder({"id","name"})
@ToString
public class UserForDB extends CommonEntity {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String role;

    @Getter @Setter
    private String createdId;

    @Getter @Setter
    private String modifiedId;

}
