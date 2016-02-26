package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;

import com.pineone.icbms.so.iot.servicerunner.controller.IBasicField;
import com.pineone.icbms.so.iot.servicerunner.controller.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.controller.TaskData;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPool;
import com.pineone.icbms.so.resources.service.AGenericService;
import com.pineone.icbms.so.resources.task.IGenericTask;

public class TaskRunner implements Executable {

	private ServiceWorkData mServiceData;
	private IRunnerListener mListener;
	private ArrayList<IGenericTask> mTaskList;
	
	private DatabaseController mDbController;
	
	public TaskRunner(ServiceWorkData serviceData) {
		mServiceData = serviceData;
		mListener = null;
		init();
	}
	
	public void setListener(IRunnerListener listener) {
		mListener = listener;
	}
	
	private void init() {
		DatabaseConnection dbConnection = DatabaseConnection.getInstance();
		mDbController = dbConnection.getController();
		
		AGenericService service = mServiceData.getService();
		mTaskList = new ArrayList<IGenericTask>(service.getTaskList());
	}

	private void updateStateRunning(String id) {
		TaskData taskData = new TaskData();
		taskData.setState(IBasicState.BASIC_STATE_RUNNING);
		taskData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, taskData);
	}
	
	private void updateState(String id, String state) {
		TaskData taskData = new TaskData();
		taskData.setState(state);
		taskData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, taskData);
	}
	
	private long setWork(ActivityRunner runner) {
		long id = 0;
		ThreadPool tp = ThreadPool.getInstance();
		while(true) {
			id = tp.setWork(runner);
			if(0 < id)
				break;
			try {
				System.out.println("[TaskRunner][setWork][wait][thread id:"+id+"]");
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
				break;
			}			
		}
		return id;
	}

	@Override
	public int execute() {
		int ret = 0;
		for(IGenericTask task : mTaskList) {
			String id = task.getId();
			ActivityRunner activityRunner = new ActivityRunner(task.getActivityList());
			activityRunner.setServiceId(mServiceData.getService().getId());
			activityRunner.setTaskId(id);
			activityRunner.setWorkData(mServiceData);
			activityRunner.setListener(new IRunnerListener() {
				
				@Override
				public void OnComplete(String id, ServiceWorkData data) {
					System.out.println("[TaskRunner][IRunnerListener][OnComplete][id:"+id+"]");
					updateState(id, IBasicState.BASIC_STATE_COMPLETE);
					mListener.OnComplete(mServiceData.getService().getId(), mServiceData);
				}

				@Override
				public void OnFail(String id, ServiceWorkData data) {
					System.out.println("[TaskRunner][IRunnerListener][OnFail][id:"+id+"]");
					updateState(id, IBasicState.BASIC_STATE_FAIL);
					mListener.OnFail(mServiceData.getService().getId(), mServiceData);
				}
			});
			
			setWork(activityRunner);
			updateStateRunning(id);
		}
		return ret;
	}
	
}
