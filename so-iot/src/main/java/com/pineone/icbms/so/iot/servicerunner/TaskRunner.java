package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;

import com.pineone.icbms.so.iot.servicerunner.db.BasicField;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.db.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.TaskDBField;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPool;
import com.pineone.icbms.so.resources.service.AGenericService;
import com.pineone.icbms.so.resources.task.IGenericTask;

public class TaskRunner implements Executable {

	private ServiceData mServiceData;
	private IRunnerListener mListener;
	private ArrayList<IGenericTask> mTaskList;
	
	private DatabaseConnection mDbConnection;
	private DatabaseController mDbController;
	
	public TaskRunner(ServiceData serviceData) {
		mServiceData = serviceData;
		mListener = null;
		init();
	}
	
	public void setListener(IRunnerListener listener) {
		mListener = listener;
	}

	private void init() {
		mDbConnection = DatabaseConnection.getInstance();
		mDbController = mDbConnection.getController();
		
		AGenericService service = mServiceData.getService();
		mTaskList = new ArrayList<IGenericTask>(service.getTaskList());
		
		for(IGenericTask task : mTaskList) {
			registerState(task.getId(), task.getName(), IBasicState.BASIC_STATE_READY);
		}				
	}
	
	private void registerState(String id, String name, String state) {
		TaskDBField taskField = new TaskDBField();
		taskField.setData(id, name, state);
		mDbController.setState(taskField);		
	}
	
	private void updateTaskState(String id, String state) {
		TaskDBField taskField = new TaskDBField();
		taskField.updateState(state);
		mDbController.updateSateById(BasicField.FIELD_TASK_ID, id, taskField);
	}

	private long setWork(ActivityRunner runner) {
		long id = 0;
		ThreadPool tp = ThreadPool.getInstance();
		while(true) {
			id = tp.setWork(runner);
			if(0 < id)
				break;
			try {
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
			ActivityRunner activityRunner = new ActivityRunner(task.getActivityList());
			activityRunner.setTaskId(task.getId());
			activityRunner.setListener(new IRunnerListener() {
				
				@Override
				public void OnComplete(String id, ServiceData data) {
					updateTaskState(id, IBasicState.BASIC_STATE_COMPLETE);
					mListener.OnComplete(mServiceData.getService().getId(), mServiceData);
				}

				@Override
				public void OnFail(String id, ServiceData data) {
					updateTaskState(id, IBasicState.BASIC_STATE_FAIL);
					mListener.OnFail(mServiceData.getService().getId(), mServiceData);
				}
			});
			
			setWork(activityRunner);
			updateTaskState(task.getId(), IBasicState.BASIC_STATE_RUNNING);
		}
		return ret;
	}
	
}
