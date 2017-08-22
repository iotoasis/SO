package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by melvin on 2017. 5. 15..
 */
//@Data
@JsonPropertyOrder({"id","contextModelId", "unit", "value"})
@ToString
public class DeviceControlForDB extends CommonEntity {
    @Getter @Setter
    private String unit;
    @Getter @Setter
    private String value;
    @Getter @Setter
    private String maxValue;
    @Getter @Setter
    private String minValue;
    @Getter @Setter
    private String unitDescription;
    @Getter @Setter
    private String valueDescription;
    @Getter @Setter
    private String contextModelId;

}
