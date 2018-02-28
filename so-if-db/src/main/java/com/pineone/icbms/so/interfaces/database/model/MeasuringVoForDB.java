package com.pineone.icbms.so.interfaces.database.model;

import lombok.*;

@ToString
public class MeasuringVoForDB{

    // rule_body
	@Getter @Setter
    private String id; //rule_body.id

    @Getter @Setter
    private String cvoType;

    @Getter @Setter
    private String baseCvoId; //rule_body.base_cvo_id

    @Getter @Setter
    private String osId;

    @Getter @Setter
    private String physicalDeviceTypeId;
    
    //@Getter @Setter
    //private String physicalDeviceTypeUri; //physical_device_type.physical_device_type_uri

    @Getter @Setter
    private String deviceId;  // == deviceUri

    @Getter @Setter
    private String locationId;  //rule_body
    
    @Getter @Setter
    private String locationUri; // location.uri

    // rule_item
    @Getter @Setter
    private String itemId;          // rule_item.id 

    @Getter @Setter
    private String ruleBodyId;  // rule_item.rule_body_id 

    @Getter @Setter
    private String voId;        // rule_item.vo_id 
    
    @Getter @Setter
    private String aspectUri;       // virtual_object.aspect_uri  

    @Getter @Setter
    private String functionalityId;  //rule_item.funtionality_id 

    @Getter @Setter
    private String functionalityUri; //functionality.uri 

    @Getter @Setter
    private String voValueType; // rule_item.vo_value_type

    @Getter @Setter
    private String voValue;     // rule_item.vo_value

}
