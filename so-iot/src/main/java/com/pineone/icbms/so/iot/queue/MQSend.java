package com.pineone.icbms.so.iot.queue;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Melvin on 2015. 11. 23..
 */


public class MQSend {

    Logger logger = LoggerFactory.getLogger(MQSend.class);


    public final static String QUEUE_NAME = "Occ Message : ";

    public void messageQueueSender(String occString) {

        logger.info("Message Queue Excute");

        RabbitmqClientConf client = new RabbitmqClientConf();
        Channel channel = client.getChannel();

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Message> messages = new ArrayList<Message>();


        messages.add(new Message(QUEUE_NAME, occString));


        System.out.println("ready to MQSend.");

        for(Message m : messages){
            try {
                channel.basicPublish(m.exchange, m.routingKey, null, m.body.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[x]" + m.body);
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
