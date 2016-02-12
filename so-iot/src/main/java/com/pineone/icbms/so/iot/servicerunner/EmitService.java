package com.pineone.icbms.so.iot.servicerunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.pineone.icbms.so.iot.servicerunner.db.DatabaseConnection;
import com.pineone.icbms.so.iot.servicerunner.db.DatabaseController;
import com.pineone.icbms.so.iot.servicerunner.db.IBasicState;
import com.pineone.icbms.so.iot.servicerunner.db.ServiceDBField;
import com.pineone.icbms.so.resources.service.AGenericService;
import com.pineone.icbms.so.resources.service.IGenericService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitService extends RabbitMessageQueueBase {

	private static EmitService mInstance = null;
	private Object mMutex = null;
	
	private DatabaseConnection mDbConnection;
	private DatabaseController mDbController;
	
	
	public static EmitService getInstance() {
		if(null == mInstance) {
			mInstance = new EmitService();
		}
		return mInstance;
	}
	
	public EmitService() {
		mMutex = new Object();
		mDbConnection = DatabaseConnection.getInstance();
		mDbController = mDbConnection.getController();
	}

	public void addService(IGenericService service) {
		ServiceDBField serviceField = new ServiceDBField();
		serviceField.setData(service.getId(), service.getName(), IBasicState.BASIC_STATE_READY);
		mDbController.setState(serviceField);			
		try {
			send(IQueueList.QUEUE_NAME_SERVICE, service);
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void send(String queueName, Object obj) throws java.io.IOException, TimeoutException {
		synchronized (mMutex) {
	        ConnectionFactory factory = new ConnectionFactory();

	        factory.setHost(getIp());
	        factory.setUsername(getUserId());
	        factory.setPassword(getPassword());
	        
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();

	        channel.queueDeclare(queueName, true, false, false, null);
	        channel.basicPublish("", queueName, null, SerializeUtil.serialize(obj));

	        channel.close();
	        connection.close();
		}
	}	
}
