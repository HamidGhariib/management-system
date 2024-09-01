package com.management.system.order.service;


import com.management.system.enumeration.OrderReqStatEnum;
import com.management.system.order.dto.request.InventoryUpdateReqDto;
import com.management.system.order.dto.request.OrderReqDto;
import com.management.system.order.dto.request.UpdateOrderStatusReqDto;
import com.management.system.order.dto.response.NewOrderResDto;
import com.management.system.order.dto.response.RequestedOrderResDto;
import com.management.system.order.exception.OrderFailedException;
import com.management.system.order.model.OrderRequest;
import com.management.system.order.repository.OrderRequestRepo;
import com.management.system.util.CommonUtilService;
import com.management.system.util.UniRestUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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

    public NewOrderResDto persistOrderReq(OrderReqDto orderReqDto) {
        logger.info("Persist order by orderReqDto " + orderReqDto);

        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setRequesterUsername(getCurrentUsername());
        orderRequest.setOrderType(orderReqDto.getOrderType());
        orderRequest.setRequestCount(orderReqDto.getRequestCount());
        orderRequest.setProductId(orderReqDto.getProductId());
        orderRequest.setStatusId(OrderReqStatEnum.REQUESTED.toString());

        String orderId = orderRequestRepo.save(orderRequest).getId().toString();

        try {
            this.updateInventory(InventoryUpdateReqDto.builder().productId(orderReqDto.getProductId())
                    .requestCount(orderReqDto.getRequestCount()).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderFailedException();
        }

        logger.info("Order orderReqDto " + orderReqDto + " persisted by orderId " + orderId);

        return NewOrderResDto.builder().orderId(orderId).message("Your request successfully submitted").build();
    }

    public void updateOrderStatus(UpdateOrderStatusReqDto updateOrderStatusReqDto) {
        orderRequestRepo.updateStatusByOrderId(updateOrderStatusReqDto.getOrderId(), updateOrderStatusReqDto.getStatus());
    }

    public RequestedOrderResDto loadRequestedOrder() {
        HashMap<String,Object> orderResMap = new HashMap<>();
        List<OrderRequest> orderRequestRes =  orderRequestRepo.getOrderRequestsByStatusId(OrderReqStatEnum.REQUESTED.toString());
        orderResMap.put("orderIdList",orderRequestRes);

        orderRequestRes.forEach(orderRequest -> this.updateOrderStatus(UpdateOrderStatusReqDto.builder().orderId(orderRequest.getId().toString()).status(OrderReqStatEnum.PENDING.toString()).build()));
        return CommonUtilService.convertModel(orderResMap,RequestedOrderResDto.class);
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