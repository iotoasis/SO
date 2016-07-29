package com.pineone.icbms.so.iot.servicerunner.db;


import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.pineone.icbms.so.iot.servicerunner.db.item.IBasicData;


public class DatabaseController {
	
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

	public String insert(IBasicData obj) {
		String ret = null;
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				mc.insertOne(obj.getData());
				ObjectId id = (ObjectId)obj.getData().get("_id");
				if(null != id)
					ret = id.toString();
			}
		}
		return ret;
	}
	
	
	public String insert(String collectionName, IBasicData obj) {
		String ret = null;
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(collectionName);
				mc.insertOne(obj.getData());
				ObjectId id = (ObjectId)obj.getData().get("_id");
				if(null != id)
					ret = id.toString();
			}
		}
		return ret;
	}
	
	public void update(String collectionName, String key, Object value, IBasicData obj) {
		synchronized (mMutex) {
			if(null != mDb) {
				Document searchQuery = new Document();
				searchQuery.append(key, value);
				MongoCollection<Document> mc = mDb.getCollection(collectionName);
				Document doc = new Document("$set", obj.getData());
				UpdateResult ur = mc.updateOne(searchQuery, doc);
			}
		}
	}
	
	public void update(String key, Object value, IBasicData obj) {
		synchronized (mMutex) {
			if(null != mDb) {
				Document searchQuery = new Document();
				searchQuery.append(key, value);
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				Document doc = new Document("$set", obj.getData());
				UpdateResult ur = mc.updateOne(searchQuery, doc);
			}
		}
	}	
	
	public void update(String collectionName, Document searchQuery, IBasicData obj) {
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(collectionName);
				Document doc = new Document("$set", obj.getData());
				UpdateResult ur = mc.updateOne((Bson) searchQuery, doc);
			}
		}
	}
	
	public void find(IBasicData obj) {
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(obj.getCollectionName());
				FindIterable<Document> fi = mc.find(obj.getData());

				MongoCursor<Document> cursor = fi.iterator();
				
				while(cursor.hasNext()) {
					Document d = cursor.next();
					for(String key : d.keySet()) {
						System.out.println("[key:"+key+"]");
					}
				}
			}
		}
	}
	
	public void find(String collectionName, Document search) {
		synchronized (mMutex) {
			if(null != mDb) {
				MongoCollection<Document> mc = mDb.getCollection(collectionName);
				FindIterable<Document> fi = mc.find(search);
				MongoCursor<Document> cursor = fi.iterator();
				
				while(cursor.hasNext()) {
					Document d = cursor.next();
					for(String key : d.keySet()) {
						System.out.println("[key:"+key+"]");
					}
				}
			}
		}
	}
	
	private MongoDatabase getDB() {
		MongoDatabase mdb = null;
		if(null != mMongoConnection)
			mdb = mMongoConnection.getDatabase(DEFAULT_DATABASE_NAME);
		return mdb;
	}
}
