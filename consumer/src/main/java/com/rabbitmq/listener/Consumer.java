package com.rabbitmq.listener;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    @RabbitListener(queues = "simple.queue")
//    public void listener(String message){
//        System.out.println("接受到消息："+message);
//    }

    @RabbitListener(queues = "simple.queue")
    public void listener1(String message) throws InterruptedException {
        System.out.println("消费者1**********接受到消息："+message);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listener2(String message) throws InterruptedException {
        System.out.println("消费者2==========接受到消息："+message);
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1"),
            exchange = @Exchange(name = "itcast.fanout", type = ExchangeTypes.FANOUT)
    ))
    public void listener3(String message){
        System.out.println("消费者1**********接受到消息："+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1"),
            exchange = @Exchange(name = "itcast.fanout", type = ExchangeTypes.FANOUT)
    ))
    public void listener4(String message){
        System.out.println("消费者2----------接受到消息："+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue2"),
            exchange = @Exchange(name = "itcast.fanout", type = ExchangeTypes.FANOUT)
    ))
    public void listener5(String message){
        System.out.println("消费者3----------接受到消息："+message);
    }
}
