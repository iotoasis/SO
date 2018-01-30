package com.pineone.icbms.so.web.model.smarthelper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT, content = JsonInclude.Include.NON_EMPTY)

public class ResSmartHelper {
	@Getter@Setter
    @JsonProperty("resultCode")
	Integer resultCode;

    @Getter@Setter
    @JsonProperty("resultMessage")
    String resultMessage;

    @Getter@Setter
    @JsonProperty("sessionId")
    String sessionId;
}

