package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pineone.icbms.so.virtualobject.common.AGenericIdNameOwner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionForIf {

    @Getter @Setter
    String function;

    @Getter @Setter
    String label;

}
