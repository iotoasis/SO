package com.pineone.icbms.so.interfaces.repository;


import com.pineone.icbms.so.resources.model.repo.service.*;

/**
 * Service Model DB Interface 
 * @see MongoConnectionPool.java
 * @Author Yunho.Bae
 * @Date 2015.12.24
 * @version 
 */
public class ServiceRepoInterface extends AMongoRepoInterface  {

	
	//Next Version :: load property file 
	
	private static final String db_Name = "ICBMS_SO";
	private static final String collection_Name = "so.resource";
	
	
	/**
	 * Constructor 
	 * @param dbName DB name
	 * @param collectionName collection name
	 */
	public ServiceRepoInterface(String dbName, String collectionName){
		setDB(dbName);
		setCollection(collectionName);
		setType();
		
	}
	
	/**
	 * Constructor 
	 */
	public ServiceRepoInterface(){
		setDB(db_Name);
		setCollection(collection_Name);
		setType();
	}
	
	/**
	 * set Mongo DB Name
	 * @param dbName : DB Name 
	 */
	@Override
	public void setDB(String dbName) {
		// TODO 
		mongoDB = getDB(dbName);
		
	}
	
	/**
	 * set Mongo DB Collection Name
	 * @param collectionName : DB Collection Name 
	 */
	@Override
	public void setCollection(String collectionName) {
		collection = collectionName;
	}
	
	/**
	 * 
	 */
	public void setType(){
		type = modelTYPE(IServiceModel.class);
	}
	
	
	
	
	
	
}
