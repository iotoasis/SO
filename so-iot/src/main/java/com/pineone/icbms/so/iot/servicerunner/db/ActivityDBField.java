package com.pineone.icbms.so.iot.servicerunner.db;

public class ActivityDBField extends BasicField {

	private final String COLLECTION_NAME = "activity";

	public void setData(String id, String name, String state) {
		setId(BasicField.FIELD_ACTIVITY_ID, id);
		setName(BasicField.FIELD_ACTIVITY_NAME, name);
		setState(state);
	}
	
	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}


}
