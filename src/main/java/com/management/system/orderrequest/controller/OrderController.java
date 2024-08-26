package com.management.system.orderrequest.controller;

import com.management.system.orderrequest.dto.request.OrderReqDto;
import com.management.system.orderrequest.dto.response.OrderResDto;
import com.management.system.orderrequest.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/new-request",method = RequestMethod.POST)
    public OrderResDto newRequest(OrderReqDto orderReqDto){
        return orderService.persistOrderReq(orderReqDto);
    }
}
