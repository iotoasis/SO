package com.pineone.icbms.so.iot.servicerunner;

import java.util.ArrayList;
import java.util.List;

import com.pineone.icbms.so.iot.servicerunner.db.ActionDBField;
import com.pineone.icbms.so.iot.servicerunner.db.BasicField;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.db.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.tp.Executable;
import com.pineone.icbms.so.resources.action.IGenericAction;
import com.pineone.icbms.so.resources.context.IGenericContext;

public class ActionRunner implements Executable {

	private ArrayList<IGenericAction> mActionList;
	private DatabaseConnection mDbConnection;
	private DatabaseController mDbController;
	private IGenericContext mActivity;
	
	public ActionRunner(List actionList) {
		mActionList = new ArrayList<IGenericAction>(actionList);
		init();
	}

	public ActionRunner(List actionList, IGenericContext activity) {
		mActionList = new ArrayList<IGenericAction>(actionList);
		mActivity = activity;
		init();
	}
	
	private void init() {
		mDbConnection = DatabaseConnection.getInstance();
		mDbController = mDbConnection.getController();
		
		for(IGenericAction action : mActionList) {
			registerState(action.getId(), action.getName(), IBasicState.BASIC_STATE_READY);
		}
	}
	
	private void registerState(String id, String name, String state) {
		ActionDBField actionField = new ActionDBField();
		actionField.setData(id, name, state);
		mDbController.setState(actionField);		
	}

	private void updateState(String id, String state) {
		ActionDBField actionField = new ActionDBField();
		actionField.updateState(state);
		mDbController.updateSateById(BasicField.FIELD_ACTION_ID, id, actionField);
	}	
	
	@Override
	public int execute() {
		int ret = 0;
		for(IGenericAction action : mActionList) {
			updateState(action.getId(), IBasicState.BASIC_STATE_RUNNING);

			action.execute(mActivity);
			
			updateState(action.getId(), IBasicState.BASIC_STATE_COMPLETE);
		}
		return ret;
	}

}
