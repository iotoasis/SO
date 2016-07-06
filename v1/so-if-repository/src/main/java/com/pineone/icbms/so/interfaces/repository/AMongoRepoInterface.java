package com.pineone.icbms.so.interfaces.repository;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jongo.Jongo;
import org.jongo.MongoCursor;
import org.jongo.Update;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.pineone.icbms.so.interfaces.repository.dao.MongoConnectionPool;

public abstract class AMongoRepoInterface implements IMongoRepo<String, Map<String, String>> {
	
	static MongoConnectionPool mongoConnectionPool;
	static MongoClient mongoClient;
	protected static DB mongoDB;
	
	protected String type;
	protected String db;
	protected String collection;
	
	
	public AMongoRepoInterface(){
		
	}
	
	/**
	 * 
	 * @param cls modelObject Class
	 * @return modelObject Class DB Collection type
	 */ 
	public <T> String modelTYPE(Class<T> cls){
		String type = "";
		try {
			Field f= cls.getField("TYPE");
			type = (String) f.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return type;
	}
	
	
	protected <V> String setKey(String key, V value, String type){
		String resultKey = "{ $and:[{type:'"+type+"'},{"+key+":"+value+"}]}";
		if(value instanceof String)resultKey = "{ $and:[{type:'"+type+"'},{"+key+":'"+value+"'}]}";
		return resultKey;
	}
	
	protected <V> String setKey(String key, V value){
		String resultKey = "{"+key+":"+value+"}";
		if(value instanceof String)resultKey = "{"+key+":'"+value+"'}";
		return resultKey;
	}
	
	protected <V> String getKeyScript(String key, V value){
		if(type == null){
			return setKey(key, value);
		}else{
			return setKey(key, value, type);
		}
	}
	
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static DB getDB(String dbName){
		mongoClient = mongoConnectionPool.getMongoClient();
		return mongoClient.getDB(dbName);
	}
	
	public abstract void setDB(String dbName);
	public abstract void setCollection(String collectionName);
	public abstract void setType();
	
	
	
	/*############################# Public Methods ################################*/
	
	/**
	 * 
	 * @param key   
	 * @param value 
	 * @param cls   
	 * @return T    
	 */
	@Override
	public <V, T> T getData(String key, V value, Class<T> cls) {
		T resultData = null;
		try{
			Jongo jongo = new Jongo(mongoDB);
			resultData = jongo.getCollection(collection).findOne(getKeyScript(key, value)).as(cls);
			jongo = null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return resultData;
	}
	
	/**
	 * 
	 * @param key   
	 * @param value 
	 * @param cls   
	 * @return      
	 */
	public <T> T getDataLike(String key, String value, Class<T> cls) {
		T resultData = null;
		try{
			Jongo jongo = new Jongo(mongoDB);
			String pKey;
			if(type == null){
				pKey = "{"+key+": {$regex: #}}";
			}else{
				pKey = "{ $and:[{type:'"+type+"'},{"+key+": {$regex: #}}]}";
			}
			String pValue = value+".*";
			resultData = jongo.getCollection(collection).findOne(pKey, pValue).as(cls);
			jongo = null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return resultData;
	}
	
	/**
	 * 
	 * @param cls
	 * @return
	 */
	public <V, T> List<T> getDataList(Class<T> cls) {
		try{
			Jongo jongo = new Jongo(mongoDB);
			String all = "{type:'"+type+"'}";
			MongoCursor<T> resultData;
			if(type == null){
				resultData = jongo.getCollection(collection).find().as(cls);
			}else{
				resultData = jongo.getCollection(collection).find(all).as(cls);
			}
			if(resultData != null){
				List<T> dataList = new ArrayList<T>();
				while(resultData.hasNext()){
					dataList.add(resultData.next());
				}
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

	/**
	 * 
	 * @param key   
	 * @param value 
	 * @param cls   
	 * @return List<T> 
	 */
	@Override
	public <V, T> List<T> getDataList(String key, V value, Class<T> cls) {
		try{
			Jongo jongo = new Jongo(mongoDB);
			MongoCursor<T> resultData = jongo.getCollection(collection).find(getKeyScript(key, value)).as(cls);
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
	
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param cls
	 * @return
	 */
	public <V, T> List<T> getDataLikeList(String key, V value, Class<T> cls) {
		try{
			Jongo jongo = new Jongo(mongoDB);
			String pKey;
			if(type == null){
				pKey = "{"+key+": {$regex: #}}";
			}else{
				pKey = "{ $and:[{type:'"+type+"'},{"+key+": {$regex: #}}]}";
			}
			String pValue = value+".*";
			MongoCursor<T> resultData = jongo.getCollection(collection).find(pKey, pValue).as(cls);
			if(resultData != null){
				List<T> dataList = new ArrayList<T>();
				while(resultData.hasNext()){
					dataList.add(resultData.next());
				}
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

	/**
	 * 
	 * @param collection
	 * @param key
	 * @param 
	 * @param data
	 */
	@Override
	public <D> Map<String, String> setData(D data) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			Jongo jongo = new Jongo(mongoDB);			
			WriteResult resultT = jongo.getCollection(collection).insert(data);
			result.put("result", "success");
			result.put("desc", resultT.toString());
			jongo = null;
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "fail");
			result.put("desc", e.toString());
		}
		return result;
	}

	/**
	 * 
	 * @param collection
	 * @param key
	 * @param data
	 * @return Object
	 */
	@Override
	public <V, D> Map<String, String> updateData(String key, V value, D data) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			Jongo jongo = new Jongo(mongoDB);
			WriteResult resultT = jongo.getCollection(collection).update(getKeyScript(key, value)).with(data);
			result.put("result", "success");
			result.put("desc", resultT.toString());
			jongo = null;
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "fail");
			result.put("desc", e.toString());
		}
		return result;
	}

	/**
	 * 
	 * @param collection
	 * @param key
	 * @return Object
	 */
	@Override
	public <V> Map<String, String> removeData(String key, V value) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			Jongo jongo = new Jongo(mongoDB);
			WriteResult resultT = jongo.getCollection(collection).remove(getKeyScript(key, value));
			result.put("result", "success");
			result.put("desc", resultT.toString());
			jongo = null;
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "fail");
			result.put("desc", e.toString());
		}
		return result;
	}
	
	

}
