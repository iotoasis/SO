package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pineone.icbms.so.iot.servicerunner.controller.ActivityData;
import com.pineone.icbms.so.iot.servicerunner.controller.IBasicField;
import com.pineone.icbms.so.iot.servicerunner.controller.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPool;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPoolListener;
import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.context.IGenericContext;

public class ActivityRunner implements Executable {

	private ArrayList<IGenericActivity> mActivityList;
	private HashMap<Long, IGenericActivity> mActivityWorkingList;
	
	private IRunnerListener mListener;
	private Object mMutex;
	
	private DatabaseController mDbController;

	private int mActivitTotalCount;
	private String mTaskId;
	private String mServiceId;

	private ServiceWorkData mServiceData;
	private boolean mIsFail;
	
	public ActivityRunner(List<IGenericActivity> activityList) {
		mActivityList = new ArrayList<IGenericActivity>(activityList);
		mActivityWorkingList = new HashMap<Long, IGenericActivity>();
		mMutex = new Object();
		
		DatabaseConnection dbConnection = DatabaseConnection.getInstance();
		mDbController = dbConnection.getController();
		mActivitTotalCount = mActivityList.size();
		setFail(false);
	}
	
	public void setListener(IRunnerListener listener) {
		mListener = listener;
	}
	
	public void setTaskId(String taskId) {
		mTaskId = taskId;
	}

	public void setServiceId(String serviceId) {
		mServiceId = serviceId;
	}

	public void setWorkData(ServiceWorkData serviceData) {
		mServiceData = serviceData;
	}
	
	private void setFail(boolean state) {
		mIsFail = state;
	}
	
	private boolean isFail() {
		return mIsFail;
	}
	
	private void updateStateRunning(String id) {
		ActivityData activityData = new ActivityData();
		activityData.setState(IBasicState.BASIC_STATE_RUNNING);
		activityData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, activityData);
	}
	
	private void updateState(String id, String state) {
		System.out.println("[ActivityRunner][updateState][OnFail][id:"+id+"][state:"+state+"]");
		ActivityData activityData = new ActivityData();
		activityData.setState(state);
		activityData.setEndTime();
		mDbController.update(IBasicField.FIELD_ID, id, activityData);
	}	
	
	private boolean isEnd(long id, String state) {
		boolean ret = false;
		synchronized (mMutex) {
			IGenericActivity activity = mActivityWorkingList.get(id);
			updateState(activity.getId(), state);
			mActivityWorkingList.remove(id);
			mActivitTotalCount--;
			if(0 == mActivitTotalCount)
				ret = true;
		}
		return ret;
	}
	
	private long setWork(IGenericActivity activity, ActionRunner runner, ThreadPoolListener listener) {
		long id = 0;
		ThreadPool tp = ThreadPool.getInstance();
		while(true) {
			id = tp.setWork(runner, listener);
			if(0 < id) {
				synchronized (mMutex) {
					mActivityWorkingList.put(id, activity);
				}			
				updateStateRunning(activity.getId());
				break;
			}
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
		for(IGenericActivity activity : mActivityList) {
			ActionRunner actionRunner = new ActionRunner(activity.getActionList(), (IGenericContext) activity);
			actionRunner.setServiceId(mServiceId);
			actionRunner.setTaskId(mTaskId);
			actionRunner.setActivityId(activity.getId());
			
			long id = setWork(activity, actionRunner, new ThreadPoolListener() {
				
				@Override
				public void OnFail(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnFail][thread id:"+id+"]");
					setFail(true);
					if(isEnd(id, IBasicState.BASIC_STATE_FAIL)) {
						if(null != mListener)
							mListener.OnFail(mTaskId, mServiceData);
					}
				}
				
				@Override
				public void OnComplete(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnComplete][thread id:"+id+"]");
					if(isEnd(id, IBasicState.BASIC_STATE_COMPLETE)) {
						if(null != mListener) {
							if(isFail()) {
								mListener.OnFail(mTaskId, mServiceData);
							}
							else {
								mListener.OnComplete(mTaskId, mServiceData);
							}
						}
					}
				}

				@Override
				public void OnThreadCrash(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnThreadCrash][thread id:"+id+"]");
					setFail(true);
					if(isEnd(id, IBasicState.BASIC_STATE_FAIL_CRASH)) {
						if(null != mListener)
							mListener.OnFail(mTaskId, mServiceData);
					}
				}
			});
			
			if(0 > id)  {
				ret = -1;
				break;
			}
		}
		return ret;		
	}

}
