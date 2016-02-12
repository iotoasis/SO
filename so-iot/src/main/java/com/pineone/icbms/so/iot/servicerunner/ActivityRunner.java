package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pineone.icbms.so.iot.servicerunner.db.ActionDBField;
import com.pineone.icbms.so.iot.servicerunner.db.ActivityDBField;
import com.pineone.icbms.so.iot.servicerunner.db.BasicField;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.db.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.ServiceDBField;
import com.pineone.icbms.so.iot.servicerunner.db.TaskDBField;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPool;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPoolListener;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.activity.IGenericActivity;
import com.pineone.icbms.so.resources.context.IGenericContext;
import com.pineone.icbms.so.resources.task.IGenericTask;

public class ActivityRunner implements Executable {

	private ArrayList<IGenericActivity> mActivityList;
	private HashMap<Long, IGenericActivity> mActivityWorkingList;
	
	private IRunnerListener mListener;
	private Object mMutex;
	
	private DatabaseConnection mDbConnection;
	private DatabaseController mDbController;

	private int mActivitTotalCount;
	private String mTaskId;

	public ActivityRunner(List<IGenericActivity> activityList) {
		mActivityList = new ArrayList<IGenericActivity>(activityList);
		mActivityWorkingList = new HashMap<Long, IGenericActivity>();
		mMutex = new Object();
		
		mDbConnection = DatabaseConnection.getInstance();
		mDbController = mDbConnection.getController();
		mActivitTotalCount = mActivityList.size();
		
		for(IGenericActivity activity : mActivityList) {
			registerState(activity.getId(), activity.getName(), IBasicState.BASIC_STATE_READY);
		}		
	}
	
	public void setListener(IRunnerListener listener) {
		mListener = listener;
	}
	
	public void setTaskId(String taskId) {
		mTaskId = taskId;
	}
	
	private boolean isEnd(long id, String state) {
		boolean ret = false;
		String activityId = null;
		synchronized (mMutex) {
			IGenericActivity activity = mActivityWorkingList.get(id);
			activityId = new String(activity.getId());
			mActivityWorkingList.remove(id);
			mActivitTotalCount--;
		}

		if(null != activityId)
			updateState(activityId, state);

		if(0 == mActivitTotalCount)
			ret = true;
		
		return ret;
	}
	
	private long setWork(IGenericActivity activity, ActionRunner runner, ThreadPoolListener listener) {
		long id = 0;
		ThreadPool tp = ThreadPool.getInstance();
		while(true) {
			id = tp.setWork(runner, listener);
			if(0 < id) {
				synchronized (mMutex) {
					System.out.println("[ActivityRunner][execute][mActivityWorkingList put][thread id:"+id+"]");
					mActivityWorkingList.put(id, activity);
				}			
				updateState(activity.getId(), IBasicState.BASIC_STATE_RUNNING);
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
	
	private void registerState(String id, String name, String state) {
		ActivityDBField activityField = new ActivityDBField();
		activityField.setData(id, name, state);
		mDbController.setState(activityField);		
	}
	
	private void updateState(String id, String state) {
		ActivityDBField activityField = new ActivityDBField();
		activityField.updateState(state);
		mDbController.updateSateById(BasicField.FIELD_ACTIVITY_ID, id, activityField);
	}
	
	
	@Override
	public int execute() {
		
		int ret = 0;
		System.out.println("[ActivityRunner][execute start]");
		
		for(IGenericActivity activity : mActivityList) {
			ActionRunner actionRunner = new ActionRunner(activity.getActionList(), (IGenericContext) activity);
			long id = setWork(activity, actionRunner, new ThreadPoolListener() {
				
				@Override
				public void OnFail(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnFail][thread id:"+id+"]");
					if(isEnd(id, IBasicState.BASIC_STATE_FAIL)) {
						if(null != mListener)
							mListener.OnComplete(mTaskId, null);
					}
				}
				
				@Override
				public void OnComplete(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnComplete][thread id:"+id+"]");
					if(isEnd(id, IBasicState.BASIC_STATE_COMPLETE)) {
						if(null != mListener)
							mListener.OnComplete(mTaskId, null);
					}
				}

				@Override
				public void OnThreadCrash(long id) {
					System.out.println("[ActivityRunner][ThreadPoolListener][OnThreadCrash][thread id:"+id+"]");
					if(isEnd(id, IBasicState.BASIC_STATE_FAIL_CRASH)) {
						if(null != mListener)
							mListener.OnComplete(mTaskId, null);
					}
				}
			});
			
			if(0 > id)  {
				ret = -1;
				break;
			}
		}
		System.out.println("[ActivityRunner][execute end]");
		return ret;		
	}

}
