package com.pineone.icbms.so.interfaces.database.model;

import lombok.*;

import java.util.List;

@ToString
public class RuleBodyForDB{

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
    
    @Getter @Setter
    private String physicalDeviceTypeUri;

    @Getter @Setter
    private String deviceId;

    @Getter @Setter
    private String deviceUri;

    @Getter @Setter
    private String locationId;  //rule_body
    
    @Getter @Setter
    private String locationUri;

    @Getter @Setter
    private List<RuleItemForDB> ruleItemForDBList;

}
