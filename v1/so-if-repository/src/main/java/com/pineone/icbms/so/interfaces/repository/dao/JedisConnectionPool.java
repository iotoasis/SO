package com.pineone.icbms.so.interfaces.repository.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisConnectionPool {
	
	static JedisPool jedisPool; 
	public JedisConnectionPool() { jedisPool = null;}
	
	public synchronized Jedis getJedisConnection() {  
		 try { 
			 if (jedisPool == null) {
				 
				 // Redis DB
				 Properties jedisInfo = new Properties();
				 String HOST = "127.0.0.1";
				 String PassWord = null;
				 int TimeOut = 10;
				 int PORT = 6379;
				 int MaxTotal = 1000;
				 int MaxIdle = 10;
				 int MinIdle = 1;
				 int MaxWaitMillis = 30000;
				 
				 try {
					String path = JedisConnectionPool.class.getResource("").getPath();
					System.out.println("path --> "+path);
					Reader reader = new FileReader(path+"db.properties");
					jedisInfo.load(reader);
					HOST = jedisInfo.getProperty("Radis_HOST");
					PORT = Integer.parseInt(jedisInfo.getProperty("Radis_PORT"));
					TimeOut = Integer.parseInt(jedisInfo.getProperty("Radis_TimeOut"));
					PassWord = jedisInfo.getProperty("Radis_PassWord");
					MaxTotal = Integer.parseInt(jedisInfo.getProperty("Radis_MaxTotal"));
					MaxIdle = Integer.parseInt(jedisInfo.getProperty("Radis_MaxIdle"));
					MinIdle = Integer.parseInt(jedisInfo.getProperty("Radis_MinIdle"));
					MaxWaitMillis =Integer.parseInt(jedisInfo.getProperty("Radis_MaxWaitMillis"));
					
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				} catch (IOException ie){
					ie.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
				
	
				 JedisPoolConfig config = new JedisPoolConfig();
				
				 config.setMaxTotal(MaxTotal); 
				 config.setMaxIdle(MaxIdle); 
				 config.setMinIdle(MinIdle); 
				 config.setMaxWaitMillis(MaxWaitMillis); 
				 jedisPool = new JedisPool(config, HOST, PORT,TimeOut, PassWord);
				 }
				 return jedisPool.getResource();
	       
			 }catch(JedisConnectionException e) { 
				e.printStackTrace();
	            throw e;
	        }
		 
	} 
	
	public synchronized void close(Jedis resource) {
		if (jedisPool != null) { 
			try{ 
				if (resource != null) { 
					//jedisPool.returnResource(resource); 
					resource.close();
					resource = null; 
					} 
			} catch (JedisConnectionException e) { 
					//jedisPool.returnBrokenResource(resource);
					resource.close();
					resource = null; 
			} finally { 
				if (resource != null)  
					//jedisPool.returnResource(resource);
					resource.close();
					resource = null; 
			} 
		} 
    } 
}
