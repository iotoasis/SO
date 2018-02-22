package com.pineone.icbms.so.web.model.measure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)

public class ResMeasuring {
	@Getter@Setter
    @JsonProperty("resultCode")
	Integer resultCode;

    @Getter@Setter
    @JsonProperty("resultMessage")
    Object resultMessage;

    @Getter@Setter
    @JsonProperty("sessionId")
    String sessionId;
}

