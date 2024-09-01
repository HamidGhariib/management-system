package com.management.system.shipping.messagingrabbitmq;

import com.management.system.shipping.ShippingLauncher;
import com.management.system.shipping.dto.response.RequestedOrderResDto;
import com.management.system.shipping.service.ShippingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitmqSender {

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = Logger.getLogger(ShippingService.class.getName());

    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(RequestedOrderResDto orderResDto){
        logger.info(String.format("Json message sent -> %s", orderResDto.toString()));
        rabbitTemplate.convertAndSend(ShippingLauncher.topicExchangeName, routingJsonKey, orderResDto);
    }
}