package com.pineone.icbms.so.iot.servicerunner.db;


import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseController extends BasicField {
	
	private final static String DEFAULT_DATABASE_NAME = "ServiceRunnerDB";
	private MongoClient mMongoConnection;
	private MongoDatabase mDb;
	private Object mMutex;
	
	public DatabaseController(MongoClient mongo) {
		mMutex = new Object();
		mMongoConnection = mongo;
		mDb = getDB();
	}
	
	public void disconnection() {
		mDb = null;
	}
	
	public String setState(BasicField obj) {
		String ret = null;
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				obj.createTimeStamp();
				mc.insertOne(obj.getDocument());
				ObjectId id = (ObjectId)obj.getDocument().get("_id");
				System.out.println("[handleDelivery][ObjectId:"+id+"]");
				if(null != id)
					ret = id.toString();
			}
		}
		return ret;
	}
	
	public void updateSateByObjectId(String id, BasicField obj) {
		synchronized (mMutex) {
			if(null != mDb) {		
				Document searchQuery = new Document();
				searchQuery.append("_id", new ObjectId(id));
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				mc.updateOne(searchQuery, obj.getDocument());
			}
		}
	}

	public void updateSateById(String idFieldName, String id, BasicField obj) {
		synchronized (mMutex) {		
			if(null != mDb) {		
				Document searchQuery = new Document();
				searchQuery.append(idFieldName, id);
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				mc.updateOne(searchQuery, obj.getDocument());
			}
		}
	}
	
	private MongoDatabase getDB() {
		MongoDatabase mdb = null;
		if(null != mMongoConnection)
			mdb = mMongoConnection.getDatabase(DEFAULT_DATABASE_NAME);
		return mdb;
	}

	@Override
	protected Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

}
