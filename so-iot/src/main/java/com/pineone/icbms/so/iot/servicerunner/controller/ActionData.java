package com.pineone.icbms.so.iot.servicerunner.controller;

import java.util.Date;

import org.bson.Document;

import com.pineone.icbms.so.iot.servicerunner.db.item.IBasicData;

public class ActionData implements IBasicData {

	public static String COLLECTION_ACTION = "action";
	
	public static String FIELD_ACTION_ID = "actionId";
	public static String FIELD_ACTION_NAME = "actionName";

	public static String FIELD_ACTION_STATE = "actionState";
	public static String FIELD_ACTION_START_TIMESTAMP = "actionStartTime";
	public static String FIELD_ACTION_END_TIMESTAMP = "actionEndTime";
	
	private Document mDoc;

	public ActionData() {
		mDoc = new Document();
	}
	
	public void setId(String id) {
		mDoc.append(FIELD_ACTION_ID, id);
	}
	
	public void setServiceId(String serviceId) {
		mDoc.append(IBasicField.FIELD_SERVICE_ID, serviceId);
	}
	
	public void setTaskId(String taskId) {
		mDoc.append(IBasicField.FIELD_TASK_ID, taskId);
	}

	public void setActivityId(String activityId) {
		mDoc.append(IBasicField.FIELD_ACTIVITY_ID, activityId);
	}
	
	public void setName(String name) {
		mDoc.append(FIELD_ACTION_NAME, name);
	}
	
	public void setState(String state) {
		mDoc.append(FIELD_ACTION_STATE, state);
	}
	
	public void setStartTime() {
		Date date = new Date();
		mDoc.append(FIELD_ACTION_START_TIMESTAMP, date);
	}
	
	public void setEndTime() {
		Date date = new Date();
		mDoc.append(FIELD_ACTION_END_TIMESTAMP, date);
	}
	
	@Override
	public Document getData() {
		return mDoc;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_ACTION;
	}
}
