package com.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage(){
        String queueName = "simple.queue";
        String message = "hello rabbitmq!!";
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("发送消息："+message);
    }

    @Test
    public void sendMessage2() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "你好，rabbitmq!";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message+i);
            Thread.sleep(20);
        }
        System.out.println("发送消息完成!");
    }

    @Test
    public void sendMessage3(){
        String exchangeName = "itcast.fanout";
        String message = "这是一条广播消息！";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        System.out.println("发送消息完成!");
    }
}
