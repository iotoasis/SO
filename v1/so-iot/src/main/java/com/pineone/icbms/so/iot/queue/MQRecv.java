package com.pineone.icbms.so.iot.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by Melvin on 2015. 11. 23..
 */


public class MQRecv {

    public final static String QUEUE_NAME = MQSend.QUEUE_NAME;

    public String recvFromMessagequeue() {


        RabbitmqClientConf client = new RabbitmqClientConf();
        Channel channel = client.getChannel();

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" [*] Waiting for messages, To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ready to receive");


            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String message = new String(delivery.getBody());
            System.out.println("[x] Received'" + message + "'");

            return message;

    }
}

