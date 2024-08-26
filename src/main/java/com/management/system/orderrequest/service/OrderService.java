package com.management.system.orderrequest.service;


import com.management.system.orderrequest.dto.request.OrderReqDto;
import com.management.system.orderrequest.dto.response.OrderResDto;
import com.management.system.orderrequest.model.OrderRequest;
import com.management.system.orderrequest.repository.OrderRequestRepo;
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
