package com.pineone.icbms.so.interfaces.repository;


import java.util.ArrayList;
import java.util.List;

import org.jongo.Jongo;
import org.jongo.MongoCursor;

import com.pineone.icbms.so.resources.model.repo.profile.*;

/**
 * Profile Model DB Interface 
 * @see MongoConnectionPool.java
 * @Author Yunho.Bae
 * @Date 2015.12.24
 * @version 
 */
public class ProfileRepoInterface extends AMongoRepoInterface  {

	
	//Next Version :: load property file 
	
	private static final String db_Name = "ICBMS_SO";
	private static final String collection_Name = "so.resource";
	
	
	/**
	 * Constructor 
	 * @param dbName DB name
	 * @param collectionName collection name
	 */
	public ProfileRepoInterface(String dbName, String collectionName){
		setDB(dbName);
		setCollection(collectionName);
		setType();
	}
	
	/**
	 * Constructor 
	 */
	public ProfileRepoInterface(){
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
		type = modelTYPE(IProfileModel.class);
	}
	
	
	public <V, T> List<T> getDataListByQuery(String query, Class<T> cls) {
		try{
			Jongo jongo = new Jongo(mongoDB);
			MongoCursor<T> resultData = jongo.getCollection(collection).find(query).as(cls);
			if(resultData != null){
				List<T> dataList = new ArrayList<T>();
				while(resultData.hasNext()){
					dataList.add(resultData.next());
				}
				resultData = null;
				jongo = null;
				return dataList;
			}else{
				jongo = null;
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
