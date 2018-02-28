package com.pineone.icbms.so.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)
public class MeasuringData {

	@Getter@Setter 
	private String devId ;
	
	@Getter@Setter 
	private String devName ;
	
	@Getter@Setter 
	private String devTypeDesc;
	
	@Getter@Setter 
	private String aspectName;
	
	@Getter@Setter 
	private String measuringValue;
}
