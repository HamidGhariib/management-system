package com.management.system.order.service;

import com.management.system.order.Launcher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        rabbitTemplate.convertAndSend(Launcher.topicExchangeName, "management.system.baz", "Hello from RabbitMQ!");
    }
}