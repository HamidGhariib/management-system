package com.management.system.shipping.messagingrabbitmq;

import com.management.system.shipping.dto.response.RequestedOrderResDto;
import com.management.system.util.CommonUtilService;
import com.management.system.util.UniRestUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "management-system")
    public void receiveMessage(Object message)
    {
        try {
            CommonUtilService.deserialize(((Message) message).getBody(), RequestedOrderResDto.class);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
