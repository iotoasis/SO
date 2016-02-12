package com.pineone.icbms.so.iot.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Melvin on 2015. 11. 23..
 */
public class RabbitmqClientConf {
    public static String HOST = "127.0.0.1"; //local

    private Connection connection = null;
    private Channel channel = null;

    public Channel getChannel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitmqClientConf.HOST);
//        factory.setUsername("pineone");
//        factory.setPassword("vkdls1dnjs");

        try {
            this.connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        try {
            this.channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.channel;

    }

    public void close() throws IOException, TimeoutException {

        this.channel.close();

        this.connection.close();
    }
}
