package org.yutil.rabbitmq;

import com.rabbitmq.client.*;
import jdk.nashorn.internal.parser.JSONParser;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class RabbitMqTest {
    private static final String RABBIT_MQ = "TEST_QUEUE";
    private static final String EXECHANGE = "TEST_EXECHANGE";
    private static final String ROUTING_KEY = "TEST_ROUTING_KEY";
    private Channel channel;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private ExecutorService executorService;
    private List<Address> addressList;

    public RabbitMqTest(Channel channel) {
        this.channel = channel;
    }

    @org.junit.Before
    public void before() {
        try {
            executorService = Executors.newCachedThreadPool();
          //  addressList = Lists.new
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            //connectionFactory.setClientProperties();
            connection = connectionFactory.newConnection(executorService, addressList);
            this.channel = connection.createChannel();
            channel.exchangeDeclare(EXECHANGE, "dirct");
            // channel.queueDeclare();
            channel.queueBind(RABBIT_MQ, EXECHANGE, ROUTING_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        int maxChannelSize = connection.getChannelMax();
    }

    public void sender(String message) {
        try {
            channel.basicPublish(EXECHANGE,ROUTING_KEY,null,message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiver() {
        try {
         Consumer consumer =    channel.getDefaultConsumer();
          //  consumer.handleDelivery();
          String message =   channel.basicConsume(RABBIT_MQ,consumer);
            JSONParser.quote(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tearDown() {
        if(channel!= null) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        if(connection!= null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
