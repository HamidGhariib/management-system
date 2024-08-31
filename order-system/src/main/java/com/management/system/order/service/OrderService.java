package com.management.system.order.service;


import com.management.system.enumeration.OrderReqStatEnum;
import com.management.system.order.dto.request.InventoryUpdateReqDto;
import com.management.system.order.dto.request.OrderReqDto;
import com.management.system.order.dto.response.OrderResDto;
import com.management.system.order.exception.OrderFailedException;
import com.management.system.order.model.OrderRequest;
import com.management.system.order.repository.OrderRequestRepo;
import com.management.system.util.UniRestUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
@Transactional
public class OrderService {


    @Value("${inventory.base.url}")
    private String inventoryBaseUrl;
    @Value("${inventory.product.update.path}")
    private String inventoryUpdatePath;
    @Value("${inventory.username}")
    private String inventoryUsername;
    @Value("${inventory.password}")
    private String inventoryPassword;

    private final OrderRequestRepo orderRequestRepo;
    private final Logger logger = Logger.getLogger(OrderService.class.getName());

    public OrderService(OrderRequestRepo orderRequestRepo) {
        this.orderRequestRepo = orderRequestRepo;
    }

    public OrderResDto persistOrderReq(OrderReqDto orderReqDto) {
        logger.info("Persist order by orderReqDto " + orderReqDto);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setRequesterUsername(getCurrentUsername());
        orderRequest.setOrderType(orderReqDto.getOrderType());
        orderRequest.setRequestCount(orderReqDto.getRequestCount());
        orderRequest.setProductId(orderReqDto.getProductId());
        orderRequest.setStatusId(OrderReqStatEnum.REQUESTED.toString());

        String  orderId = orderRequestRepo.save(orderRequest).getId().toString();
        try {
            this.updateInventory(InventoryUpdateReqDto.builder().productId(orderReqDto.getProductId())
                    .requestCount(orderReqDto.getRequestCount()).build());
        }catch (Exception e){
            e.printStackTrace();
            throw new OrderFailedException();
        }

        logger.info("Order orderReqDto " + orderReqDto + " persisted by orderId " + orderId);

        return OrderResDto.builder().orderId(orderId).message("Your request successfully submitted").build();
    }

    public void updateRequestStatus(String orderId, OrderReqStatEnum status) {
        orderRequestRepo.updateStatusByOrderId(orderId, status.toString());
    }

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void updateInventory(InventoryUpdateReqDto inventoryUpdateReqDto) {
        UniRestUtils.post(inventoryBaseUrl + inventoryUpdatePath, inventoryUpdateReqDto, new HashMap<>() {{
            put("Content-Type", "application/json");
            put("Authorization", "Basic " + Base64.getEncoder().encodeToString((inventoryUsername + ":" + inventoryPassword).getBytes()));
        }});
    }
}
