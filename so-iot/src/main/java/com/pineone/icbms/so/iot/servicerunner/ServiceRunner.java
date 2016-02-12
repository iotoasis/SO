package com.pineone.icbms.so.iot.servicerunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import com.pineone.icbms.so.iot.servicerunner.db.BasicField;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.db.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.ServiceDBField;
import com.pineone.icbms.so.iot.servicerunner.tp.ThreadPool;
import com.pineone.icbms.so.resources.service.AGenericService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ServiceRunner extends RabbitMessageQueueBase {
	private static ServiceRunner mInstance = null;
	
	private int THREAD_MAX_COUNT = 100; 

	private Connection mRecvConnection;
	private HashMap<String, Channel> mRecvChannel;
	private HashMap<String, Consumer> mConsumer;
	private int	mThreadMaxcount;
	
	private DatabaseConnection mDbConnection;
	private DatabaseController mDbController;
	
	public static ServiceRunner getInstance() {
	  if(null == mInstance) {
		  mInstance = new ServiceRunner();
	  }
	  return mInstance;
	}
	
	public ServiceRunner() {
		System.out.println("Service Manager");
		mRecvChannel = new HashMap<String, Channel>();
		mConsumer = new HashMap<String, Consumer>();
		
		mDbConnection = DatabaseConnection.getInstance();
		mDbController = mDbConnection.getController();
		
		mThreadMaxcount = THREAD_MAX_COUNT;
	}
	
	public void setThreadMaxCount(int threadCount) {
		mThreadMaxcount = threadCount;
	}
	
	public void start() {
		ThreadPool tp = ThreadPool.getInstance();
		tp.setThreadCount(mThreadMaxcount);
		tp.createThreadPool();
		
	    try {
			recv();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		System.out.println("[ServiceRunner][destroy start]");
		try {
			destroyChannel();
			mRecvConnection.close();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mRecvChannel.clear();
		mRecvChannel = null;
		
		mConsumer.clear();
		mConsumer = null;
		
		ThreadPool.getInstance().destroy();
		mDbConnection.disConnection();
		System.out.println("[ServiceRunner][destroy end]");
	}
	
	private void destroyChannel() throws IOException, TimeoutException {
		for(String key : mRecvChannel.keySet()) {
			Channel channel = mRecvChannel.get(key);
			channel.close();
		}
	}
	
	private Channel createChannel(Connection connection) throws IOException {
		return connection.createChannel();
	}
	
	private void setQueue(Channel channel, String queueName, Consumer consumer) throws IOException {

		channel.queueDeclare(queueName, true, false, false, null);
		channel.basicQos(1);
		channel.basicConsume(queueName, false, consumer);
		
	    mRecvChannel.put(queueName, channel);
	}
	
	private void recv() throws IOException, TimeoutException {
	    ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(getIp());
        factory.setUsername(getUserId());
        factory.setPassword(getPassword());
	    
        mRecvConnection = factory.newConnection();
        
        Channel serviceChannel = createChannel(mRecvConnection);
        Consumer serviceConsumer = new DefaultConsumer(serviceChannel) {
			
        	@Override
        	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
        			throws IOException {

        		AGenericService as = (AGenericService) SerializeUtil.unserialize(body);
        		System.out.println("[serviceConsumer][handleDelivery][service id:"+as.getId()+"]");
        		System.out.println("[serviceConsumer][handleDelivery][service name:"+as.getName()+"]");
        		
	    		System.out.println("[serviceConsumer][handleDelivery][getExchange:"+envelope.getExchange()+"]");
	    		System.out.println("[serviceConsumer][handleDelivery][getDeliveryTag:"+envelope.getDeliveryTag()+"]");
	    		System.out.println("[serviceConsumer][handleDelivery][getRoutingKey:"+envelope.getRoutingKey()+"]");
	    		System.out.println("[serviceConsumer][handleDelivery][consumerTag:"+consumerTag+"]");
	    		
	    		ServiceData serviceData = new ServiceData(as, consumerTag, envelope.getDeliveryTag());
	    		serviceData.setRoutingKey(envelope.getRoutingKey());
	    		
	    		ThreadPool tp = ThreadPool.getInstance();
	    		
	    		if(0 < tp.getAvailableThreadCount()) {
	    			
		    		ServiceDBField serviceField = new ServiceDBField();
		    		serviceField.updateState(IBasicState.BASIC_STATE_RUNNING);
		    		mDbController.updateSateById(BasicField.FIELD_SERVICE_ID, as.getId(), serviceField);				    		
	    			
	    		
		    		TaskRunner taskRunner = new TaskRunner(serviceData);
		    		taskRunner.setListener(new IRunnerListener() {
						
						@Override
						public void OnComplete(String id, ServiceData data) {
			        		Channel channel = mRecvChannel.get(data.getRoutingKey());
				    		try {
				    			System.out.println("[serviceConsumer][IServiceManagerListener][Ack Ok]");
								channel.basicAck(data.getDeliveryTag(), false);
							} catch (IOException e) {
								e.printStackTrace();
							}
				    		
				    		ServiceDBField serviceField = new ServiceDBField();
				    		serviceField.updateState(IBasicState.BASIC_STATE_COMPLETE);
				    		mDbController.updateSateById(BasicField.FIELD_SERVICE_ID, id, serviceField);				    		
						}

						@Override
						public void OnFail(String id, ServiceData data) {
			        		Channel channel = mRecvChannel.get(data.getRoutingKey());
				    		try {
				    			System.out.println("[serviceConsumer][IServiceManagerListener][Ack Ok]");
								channel.basicAck(data.getDeliveryTag(), false);
							} catch (IOException e) {
								e.printStackTrace();
							}							
				    		ServiceDBField serviceField = new ServiceDBField();
				    		serviceField.updateState(IBasicState.BASIC_STATE_FAIL);
				    		mDbController.updateSateById(BasicField.FIELD_SERVICE_ID, id, serviceField);				    		
				    		
						}
					});
		    		tp.setWork(taskRunner);
	    		}
	    		else {
	    			System.out.println("[serviceConsumer][handleDelivery][reject]");
	    			Channel channel = mRecvChannel.get(envelope.getRoutingKey());
	    			channel.basicReject(envelope.getDeliveryTag(), true);
	    		}
	    		
        	}
		};
		
		setQueue(serviceChannel, IQueueList.QUEUE_NAME_SERVICE, serviceConsumer);
        mConsumer.put(IQueueList.QUEUE_NAME_SERVICE, serviceConsumer);
	}
}
