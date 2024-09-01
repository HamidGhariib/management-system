package com.management.system.shipping.service;


import com.google.gson.Gson;
import com.management.system.shipping.dto.response.RequestedOrderResDto;
import com.management.system.shipping.messagingrabbitmq.RabbitmqSender;
import com.management.system.util.UniRestUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class ShippingService {

    private final Logger logger = Logger.getLogger(ShippingService.class.getName());

    @Value("${order.system.base.url}")
    private String orderSysBaseUrl;
    @Value("${order.system.load.pend.request.path}")
    private String loadPendPath;
    @Value("${order.system.username}")
    private String orderSysUsername;
    @Value("${order.system.password}")
    private String orderSysPassword;

    private final RabbitmqSender rabbitmqSender;


    public ShippingService(RabbitmqSender rabbitmqSender) {
        this.rabbitmqSender = rabbitmqSender;
    }

    @PostConstruct
    public void loadPendingOrder() {
        String loadRes = UniRestUtils.get(orderSysBaseUrl + loadPendPath, null, new HashMap<>() {{
            put("Content-Type", "application/json");
            put("Authorization", "Basic " + Base64.getEncoder().encodeToString((orderSysUsername + ":" + orderSysPassword).getBytes()));
        }});

        RequestedOrderResDto orderResDto = new Gson().fromJson(loadRes, RequestedOrderResDto.class);

        rabbitmqSender.sendMessage(orderResDto);
    }
}
