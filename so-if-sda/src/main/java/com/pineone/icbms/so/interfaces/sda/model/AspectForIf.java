package com.pineone.icbms.so.interfaces.sda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AspectForIf {

    @Getter @Setter
    String aspect;

    @Getter @Setter
    String label;

}
