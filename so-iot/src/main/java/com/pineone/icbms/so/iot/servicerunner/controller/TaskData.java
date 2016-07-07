package com.pineone.icbms.so.iot.servicerunner.controller;

import java.util.Date;

import org.bson.Document;

import com.pineone.icbms.so.iot.servicerunner.db.item.IBasicData;


public class TaskData implements IBasicData {

	public static String COLLECTION_TASK = "task";
	
	private Document mDoc;

	public TaskData() {
		mDoc = new Document();
	}
	
	public void setId(String id) {
		mDoc.append(IBasicField.FIELD_ID, id);
	}
	
	public void setServiceId(String serviceId) {
		mDoc.append(IBasicField.FIELD_SERVICE_ID, serviceId);
	}
	
	public void setName(String name) {
		mDoc.append(IBasicField.FIELD_NAME, name);
	}
	
	public void setState(String state) {
		mDoc.append(IBasicField.FIELD_STATE, state);
	}
	
	public void setSubject(String key, Object list) {
		mDoc.append(key, list);
	}
	
	public void setStartTime() {
		Date date = new Date();
		mDoc.append(IBasicField.FIELD_START_TIMESTAMP, date);
	}
	
	public void setEndTime() {
		Date date = new Date();
		mDoc.append(IBasicField.FIELD_END_TIMESTAMP, date);
	}
	
	@Override
	public Document getData() {
		return mDoc;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_TASK;
	}
}
