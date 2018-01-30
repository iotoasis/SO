/*
 * Created Date : 2017. 9. 7
 * Author : jonghee
 * Company : PINEONE
 * License : MIT
 */

package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * The type Virtual object for db.
 * jonghee 2017.09.07.
 */
//@Data
//@EqualsAndHashCode(callSuper=true)
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class VirtualObjectForDB extends CommonEntity {

    @Getter @Setter
    private String aspectUri;

    @Getter @Setter
    private String functionalityId;

    @Getter @Setter
    private String functionalityUri;

    @Getter @Setter
    private String voValueType; // rule_item.vo_value_type

    @Getter @Setter
    private String voValue;     // rule_item.vo_value
}
