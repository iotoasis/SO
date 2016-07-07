package com.pineone.icbms.so.interfaces.repository;


/**
 * DriverResult Model DB Interface 
 * @see MongoConnectionPool.java
 * @Author Yunho.Bae
 * @Date 2015.01.07
 * @version 
 */
public class DriverResultRepoInterface extends AMongoRepoInterface  {

	
	//Next Version :: load property file 
	
	private static final String db_Name = "ICBMS_SO";
	private static final String collection_Name = "DriverResult";
	
	
	/**
	 * Constructor 
	 * @param dbName DB name
	 * @param collectionName collection name
	 */
	public DriverResultRepoInterface(String dbName, String collectionName){
		setDB(dbName);
		setCollection(collectionName);
	}
	
	/**
	 * Constructor 
	 */
	public DriverResultRepoInterface(){
		setDB(db_Name);
		setCollection(collection_Name);
	}
	
	/**
	 * set Mongo DB Name
	 * @param dbName : DB Name 
	 */
	@Override
	public void setDB(String dbName) {
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
		// no Type
	}

	
}
