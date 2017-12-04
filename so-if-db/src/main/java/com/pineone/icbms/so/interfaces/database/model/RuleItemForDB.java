package com.pineone.icbms.so.interfaces.database.model;

import lombok.*;

@ToString
public class RuleItemForDB {

    @Getter @Setter
    private String id;          // rule_item.id 

    @Getter @Setter
    private String ruleBodyId;   // rule_item.rule_body_id 

    @Getter @Setter
    private String voId;        // rule_item.vo_id 
    
    @Getter @Setter
    private String aspectUri;  

    @Getter @Setter
    private String funtionalityId; // rule_item.funtionality_id 

    @Getter @Setter
    private String funtionalityUri; 

    @Getter @Setter
    private String voValueType; // rule_item.vo_value_type

    @Getter @Setter
    private String voValue;     // rule_item.vo_value
}
