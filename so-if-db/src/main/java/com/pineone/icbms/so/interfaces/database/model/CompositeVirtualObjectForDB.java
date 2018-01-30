package com.pineone.icbms.so.interfaces.database.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pineone.icbms.so.interfaces.database.model.CommonEntity;
import lombok.*;

import java.util.List;

/**
 * Composite Virtual Object model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 13..
 */
//@Data
@JsonPropertyOrder({"id","name", "description"})
@ToString
public class CompositeVirtualObjectForDB extends CommonEntity {
	//id = rule_body.id

    @Getter @Setter
    private String cvoType;

    @Getter @Setter
    private String physicalDeviceTypeId;
    
    @Getter @Setter
    private String deviceId;

	
    @Getter @Setter
    private String baseCvoId;	//rule_body.cvoId

    @Getter @Setter
    private String locationId;  //rule_body

    @Getter @Setter
    private String osId;
    
    @Getter @Setter
    private List<VirtualObjectForDB> virtualObjectForDBList;

}
