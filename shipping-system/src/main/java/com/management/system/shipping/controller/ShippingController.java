package com.management.system.shipping.controller;

import com.management.system.shipping.dto.request.InventoryReqDto;
import com.management.system.shipping.dto.response.OrderResDto;
import com.management.system.shipping.service.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/v1/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public OrderResDto updateInventory(ShippingService shippingService){
        return null;
    }
}
