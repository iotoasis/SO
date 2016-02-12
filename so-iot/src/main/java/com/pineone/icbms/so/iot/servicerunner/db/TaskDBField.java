package com.pineone.icbms.so.iot.servicerunner.db;

public class TaskDBField extends BasicField {

	private final String COLLECTION_NAME = "task";
	
	public void setData(String id, String name, String state) {
		setId(BasicField.FIELD_TASK_ID, id);
		setName(BasicField.FIELD_TASK_NAME, name);
		setState(state);
	}	
	
	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}
}
