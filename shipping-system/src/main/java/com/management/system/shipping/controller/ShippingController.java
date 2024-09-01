package com.management.system.shipping.controller;

import com.management.system.shipping.service.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/api/shipping/v1")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public void updateInventory(ShippingService shippingService){}
}
