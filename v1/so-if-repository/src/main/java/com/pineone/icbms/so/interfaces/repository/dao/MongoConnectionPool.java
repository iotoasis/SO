package com.pineone.icbms.so.interfaces.repository.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;


public class MongoConnectionPool {
	
	static MongoClient mongoClient; 
	public MongoConnectionPool() {
		if(mongoClient != null)mongoClient.close();
		mongoClient = null;
		try {
			setMongoConnection();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final void setMongoConnection() throws UnknownHostException{
		
		System.out.println("call setMongoConnection");
		//mongo DB
		String HOST = "166.104.112.37";
		int PORT = 37017;
		int connectPerHost = 10;


		MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
		try {
			Properties mongoInfo = new Properties();
			//String path = MongoConnectionPool.class.getResource("").getPath();
			//Reader reader = new FileReader(path+"db.properties");
			//mongoInfo.load(reader);
			mongoInfo.load(MongoConnectionPool.class.getResourceAsStream("db.properties"));
			HOST = mongoInfo.getProperty("Mongo_HOST");
			PORT = Integer.parseInt(mongoInfo.getProperty("Mongo_PORT"));
			
			connectPerHost = Integer.parseInt(mongoInfo.getProperty("Mongo_ConnectPerHost"));
			
			optionsBuilder.connectionsPerHost(connectPerHost);
			
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie){
			ie.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		MongoClientOptions options = optionsBuilder.build();

		mongoClient = new MongoClient(new ServerAddress(HOST, PORT), options);

	}
	
	private static final class SingletonConnection{
		static final MongoConnectionPool singletonC = new MongoConnectionPool();
	}
	
	public static MongoClient getMongoClient(){
		return SingletonConnection.singletonC.mongoClient;
	}
	
}
