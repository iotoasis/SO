package com.pineone.icbms.so.web.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class ContextModelTransFormObject {
	@Setter @Getter
	private String cmd;
	@Setter @Getter
	private String contextId;
	@Setter @Getter
	private List<Content> contents;
	@Setter @Getter
	private String time;
}
