package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.iot.servicerunner.controller.ActionData;
import com.pineone.icbms.so.iot.servicerunner.controller.IBasicField;
import com.pineone.icbms.so.iot.servicerunner.controller.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.context.IGenericContext;

public class ActionRunner implements Executable {

	private ArrayList<IGenericAction> mActionList;
	private DatabaseController mDbController;
	private IGenericContext mActivity;
	
	private String mTaskId;
	private String mServiceId;
	private String mActivityId;
	
	public ActionRunner(List actionList) {
		mActionList = new ArrayList<IGenericAction>(actionList);
		init();
	}

	public ActionRunner(List actionList, IGenericContext activity) {
		mActionList = new ArrayList<IGenericAction>(actionList);
		mActivity = activity;
		init();
	}

	public void setTaskId(String taskId) {
		mTaskId = taskId;
	}

	public void setServiceId(String serviceId) {
		mServiceId = serviceId;
	}	
	
	public void setActivityId(String activityId) {
		mActivityId = activityId;
	}

	private void updateStateRunning(String id) {
		ActionData actionData = new ActionData();
		actionData.setState(IBasicState.BASIC_STATE_RUNNING);
		actionData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, actionData);
	}
	
	private void updateState(String id, String state) {
		ActionData actionData = new ActionData();
		actionData.setState(state);
		actionData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, actionData);
	}		
	
	
	private void init() {
		DatabaseConnection dbConnection = DatabaseConnection.getInstance();
		mDbController = dbConnection.getController();
	}
	
	@Override
	public int execute() {
		int ret = 0;
		for(IGenericAction action : mActionList) {
			updateStateRunning(action.getId());
			action.execute(mActivity);
			updateState(action.getId(), IBasicState.BASIC_STATE_COMPLETE);
		}
		return ret;
	}

}
