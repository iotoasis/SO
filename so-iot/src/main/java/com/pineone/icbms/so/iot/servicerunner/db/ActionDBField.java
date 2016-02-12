package com.pineone.icbms.so.iot.servicerunner.db;

public class ActionDBField extends BasicField {

	private final String COLLECTION_NAME = "action";

	
	public void setData(String id, String name, String state) {
		setId(BasicField.FIELD_ACTION_ID, id);
		setName(BasicField.FIELD_ACTION_NAME, name);
		setState(state);
	}
	
	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}
}
