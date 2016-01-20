package com.pineone.icbms.so.iot.servicerunner.db;

import org.bson.*;

public class ServiceDBField extends BasicField {

	private final String COLLECTION_NAME = "service";

	public void setData(String id, String name, String state) {
		setId(BasicField.FIELD_SERVICE_ID, id);
		setName(BasicField.FIELD_SERVICE_NAME, name);
		setState(state);
	}	
	
	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}
}
