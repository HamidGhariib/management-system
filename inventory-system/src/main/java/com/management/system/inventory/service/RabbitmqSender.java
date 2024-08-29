package com.management.system.inventory.service;

import com.management.system.inventory.InventoryLauncher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(){
        rabbitTemplate.convertAndSend(InventoryLauncher.topicExchangeName, "management.system.baz", "Hello from RabbitMQ!");
    }
}