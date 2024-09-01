package com.management.system.shipping.messagingrabbitmq;

import com.management.system.shipping.dto.request.OrderReqDto;
import com.management.system.shipping.dto.response.RequestedOrderResDto;
import com.management.system.shipping.service.ShippingService;
import com.management.system.util.CommonUtilService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer {

    @Lazy
    private final ShippingService shippingService;

    public RabbitMQConsumer(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @RabbitListener(queues = {"${shipping.queues.name}"})
    public void consumeJsonMessage(RequestedOrderResDto orderResDto) {
        shippingService.shippingOrder(CommonUtilService.convertModel(orderResDto, OrderReqDto.class));
    }
}
