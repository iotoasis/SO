package com.pineone.icbms.so.interfaces.database.model;

import lombok.*;

import java.util.List;

@ToString
public class NonDeviceCvoForDB extends RuleBodyForDB{

	@Getter @Setter
    private String nonCvoId; //rule_body.id

    @Getter @Setter
    private String orgOsId;
}
