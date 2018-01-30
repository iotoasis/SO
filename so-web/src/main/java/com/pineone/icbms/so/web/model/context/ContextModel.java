package com.pineone.icbms.so.web.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class ContextModel {
	@Getter @Setter
	private String id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private List<String> domainIdList;
	@Getter @Setter
	private String contextType;
	@Getter @Setter
	private String createTime;
	@Getter @Setter
	private String modifiedTime;
	@Getter @Setter
	private String occTime;
	
	public ContextModel() {
	}
	
	public ContextModel(String id, List<String> domainIdList, String contextType, String occTime) {
		this.id = id;
		this.domainIdList = domainIdList;
		this.contextType = contextType;
		this.occTime = occTime;
	}
	
	public ContextModel(String id, String name, List<String> domainIdList, String contextType) {
		this.id = id;
		this.name = name;
		this.domainIdList = domainIdList;
		this.contextType = contextType;
	}
	
	public ContextModel(String id, String name, List<String> domainIdList, String contextType, String createTime, String modifiedTime) {
		this.id = id;
		this.name = name;
		this.domainIdList = domainIdList;
		this.contextType = contextType;
		this.createTime = createTime;
		this.modifiedTime = modifiedTime;
	}
}
