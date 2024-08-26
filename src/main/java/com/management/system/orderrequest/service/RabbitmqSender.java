package com.management.system.orderrequest.service;

import com.management.system.orderrequest.OrderRequestApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        rabbitTemplate.convertAndSend(OrderRequestApplication.topicExchangeName, "management.system.baz", "Hello from RabbitMQ!");
    }
}