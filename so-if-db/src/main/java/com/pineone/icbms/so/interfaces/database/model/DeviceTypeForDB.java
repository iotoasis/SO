package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


/**
 * Created by melvin on 2017. 4. 21..
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class DeviceTypeForDB extends CommonEntity {
    @Getter @Setter
    private String physicalDeviceTypeUri;
}
