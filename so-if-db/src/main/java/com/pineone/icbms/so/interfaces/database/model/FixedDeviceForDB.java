package com.pineone.icbms.so.interfaces.database.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * fixed_device model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"fixedDeviceId","name", "description"})
@ToString
public class FixedDeviceForDB extends CommonEntity {

    @Setter @Getter
    private String fixedDeviceId;

    @Setter @Getter
    private String profileId;

    @Setter @Getter
    String deviceUri;

    @Setter @Getter
    private String virtualObjectId;

}
