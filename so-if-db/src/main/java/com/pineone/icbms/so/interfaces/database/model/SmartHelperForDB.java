package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * Created by gibubi on 2017. 12. 11.
 */

@JsonPropertyOrder({"id","name", "description"})
@ToString
public class SmartHelperForDB extends CommonEntity {

    @Setter @Getter
    private String locationId;
    
    @Setter @Getter
    private String orchestrationServiceId;
    
    @Setter @Getter
    private String successMsg;
    
    @Setter @Getter
    private String failMsg;
}
