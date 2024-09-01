package com.management.system.shipping.service;


import com.google.gson.Gson;
import com.management.system.shipping.dto.request.OrderReqDto;
import com.management.system.shipping.dto.request.UpdateOrderStatusReqDto;
import com.management.system.shipping.dto.response.RequestedOrderResDto;
import com.management.system.shipping.messagingrabbitmq.RabbitmqSender;
import com.management.system.util.UniRestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Value("${order.system.update.status.path}")
    private String updateOrderStatusPath;
    @Value("${order.system.username}")
    private String orderSysUsername;
    @Value("${order.system.password}")
    private String orderSysPassword;

    private final RabbitmqSender rabbitmqSender;


    public ShippingService(RabbitmqSender rabbitmqSender) {
        this.rabbitmqSender = rabbitmqSender;
    }

    private void loadPendingOrderAndPublish() {
        String loadRes = UniRestUtils.get(orderSysBaseUrl + loadPendPath, null, this.headers() );

        RequestedOrderResDto orderResDto = new Gson().fromJson(loadRes, RequestedOrderResDto.class);
        logger.info("Shipping service loaded "+orderResDto.getOrderIdList().size()+" order");

        rabbitmqSender.sendMessage(orderResDto);
    }

    public void shippingOrder(OrderReqDto orderReqDto){
        orderReqDto.getOrderIdList().forEach(orderDto -> UniRestUtils.post(orderSysBaseUrl+updateOrderStatusPath,
                UpdateOrderStatusReqDto.builder().orderId(orderDto.getId()).status("SHIPPED").build(),this.headers()));
    }

    private HashMap<String,String> headers(){
     return new HashMap<>() {{
            put("Content-Type", "application/json");
            put("Authorization", "Basic " + Base64.getEncoder().encodeToString((orderSysUsername + ":" + orderSysPassword).getBytes()));
        }};
    }
    @Scheduled(cron = "0 */1 * * * *")
    private void shippingService(){
        logger.info("Shipping service is now running....");
        this.loadPendingOrderAndPublish();
    }
}
