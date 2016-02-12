package com.pineone.icbms.so.iot.servicerunner.db;

import java.util.Date;

import org.bson.Document;

abstract public class BasicField {

	public static String FIELD_ID = "id";
	public static String FIELD_NAME = "name";
	
	
	public static String FIELD_SERVICE_ID = "serviceId";
	public static String FIELD_SERVICE_NAME = "serviceName";

	public static String FIELD_TASK_ID = "taskId";
	public static String FIELD_TASK_NAME = "taskName";

	public static String FIELD_ACTIVITY_ID = "activityId";
	public static String FIELD_ACTIVITY_NAME = "activityName";

	public static String FIELD_ACTION_ID = "actionId";
	public static String FIELD_ACTION_NAME = "actionName";

	public static String FIELD_STATE = "state";
	public static String FIELD_TIME_STAMP = "time";
	
	private Document mDocument;
	
	public BasicField() {
		mDocument = new Document();
	}

	public void setId(String idFieldName, String id) {
		mDocument.append(idFieldName, id);
	}
	
	public void setName(String idFieldName, String name) {
		mDocument.append(idFieldName, name);
	}
	
	public void setState(String state) {
		mDocument.append(BasicField.FIELD_STATE, state);
	}

	public void updateState(String state) {
		mDocument.append("$set", new Document().append(BasicField.FIELD_STATE, state));
	}
	
	protected Document getDocument() {
		return mDocument;
	}
	
	protected void createTimeStamp() {
		Date now = new Date();
		mDocument.append(FIELD_TIME_STAMP, now);
	}
	
	protected abstract String getCollectionName();
}
