package com.management.system.shipping.messagingrabbitmq;

import com.management.system.shipping.ShippingLauncher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Object object){
        rabbitTemplate.convertAndSend(ShippingLauncher.topicExchangeName, "management.system.order", object);
    }
}