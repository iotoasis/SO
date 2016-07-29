package com.pineone.icbms.so.iot.servicerunner.controller;

import java.util.List;

import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.service.IGenericService;
import com.pineone.icbms.so.resources.task.IGenericTask;

public class ServiceController {

	private IGenericService mData;
	
	private DatabaseController mDBController;
	
	public ServiceController(IGenericService data) {
		
	    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
	    mDBController = dbConnection.getController();		
		
		mData = data;
	}

	public void createServiceDataBase() {
		ServiceData serviceData = new ServiceData();
		String id = mData.getId();
		serviceData.setId(id);
		serviceData.setName(mData.getName());
		serviceData.setState(IBasicState.BASIC_STATE_READY);
		serviceData.setStartTime();
		serviceData.setEndTime();
		mDBController.insert(serviceData);
		
		createTaskDatabase(id, mData.getTaskList());
	}
	
	private void createTaskDatabase(String serviceId, List<IGenericTask> taskList) {
		for(IGenericTask task : taskList) {
			TaskData taskData = new TaskData();
			String id = task.getId();
			taskData.setServiceId(serviceId);
			taskData.setId(id);
			taskData.setName(task.getName());
			taskData.setState(IBasicState.BASIC_STATE_READY);
			taskData.setStartTime();
			taskData.setEndTime();
			mDBController.insert(taskData);
			
			createActivityDatabase(serviceId, id, task.getActivityList());
		}
	}

	private void createActivityDatabase(String serviceId, String taskId, List<IGenericActivity> activityList) {
		for(IGenericActivity activity : activityList) {
			ActivityData activityData = new ActivityData();
			String id = activity.getId();
			activityData.setServiceId(serviceId);
			activityData.setTaskId(taskId);
			activityData.setId(id);
			activityData.setName(activity.getName());
			activityData.setState(IBasicState.BASIC_STATE_READY);
			activityData.setStartTime();
			activityData.setEndTime();
			mDBController.insert(activityData);
			
			createActionDatabase(serviceId, taskId, id, activity.getActionList());
		}
	}
	
	private void createActionDatabase(String serviceId, String taskId, String activityId, List<IGenericAction> actionList) {
		for(IGenericAction action : actionList) {
			ActionData actionData = new ActionData();
			String id = action.getId();
			actionData.setServiceId(serviceId);
			actionData.setTaskId(taskId);
			actionData.setActivityId(activityId);
			actionData.setId(id);
			actionData.setName(action.getName());
			actionData.setState(IBasicState.BASIC_STATE_READY);
			actionData.setStartTime();
			actionData.setEndTime();
			mDBController.insert(actionData);
		}
	}

}
