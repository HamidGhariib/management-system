package com.management.system.shipping.service;

import com.management.system.shipping.ShippingLauncher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        rabbitTemplate.convertAndSend(ShippingLauncher.topicExchangeName, "management.system.baz", "Hello from RabbitMQ!");
    }
}