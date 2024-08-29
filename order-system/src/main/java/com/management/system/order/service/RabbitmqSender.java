package com.management.system.order.service;

import com.management.system.order.OrderLauncher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        rabbitTemplate.convertAndSend(OrderLauncher.topicExchangeName, "management.system.baz", "Hello from RabbitMQ!");
    }
}