package com.pineone.icbms.so.web.interfaces.api.service.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Content {
	@Setter @Getter
	String loc;
	@Setter @Getter
	String uri;
	@Setter @Getter
	String type;
	@Setter @Getter
	String deg;
	@Setter @Getter
	String place;
}
