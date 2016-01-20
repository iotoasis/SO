package com.pineone.icbms.so.iot.servicerunner.db;

import com.mongodb.MongoClient;

public class DatabaseConnection extends DBConnectionInfo {
	
	private static DatabaseConnection mInstance = null;
	
	private MongoClient mMongo = null;
	private DatabaseController mDBController = null;
	
	public static DatabaseConnection getInstance() {
	  if(null == mInstance) {
		  mInstance = new DatabaseConnection();
	  }
	  return mInstance;
	}
		
	public DatabaseConnection() {
		mMongo = new MongoClient(getIp(), getPort());
		mDBController = new DatabaseController(mMongo); 
	}
	
	public DatabaseController getController() {
		return mDBController; 
	}
	
	public void disConnection() {
		if(null != mMongo)
			mMongo.close();
		
		if(null != mDBController)
			mDBController.disconnection();
	}
}
