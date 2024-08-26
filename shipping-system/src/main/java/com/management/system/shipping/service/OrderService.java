package com.management.system.shipping.service;


import com.management.system.shipping.dto.request.OrderReqDto;
import com.management.system.shipping.dto.response.OrderResDto;
import com.management.system.shipping.model.OrderRequest;
import com.management.system.shipping.repository.OrderRequestRepo;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderService {

    private final OrderRequestRepo orderRequestRepo;
    private final Logger logger = Logger.getLogger(OrderService.class.getName());

    public OrderService(OrderRequestRepo orderRequestRepo) {
        this.orderRequestRepo = orderRequestRepo;
    }

    public OrderResDto persistOrderReq(OrderReqDto orderReqDto) {
        logger.info("Persist order by orderReqDto "+orderReqDto);
        OrderResDto orderResDto = new OrderResDto();
        String orderId = orderRequestRepo.save(OrderRequest.builder().build()).getId().toString();
        logger.info("Order orderReqDto "+orderReqDto+" persisted by orderId "+orderId);
        orderResDto.setOrderId(orderId);
        return orderResDto;
    }
}
