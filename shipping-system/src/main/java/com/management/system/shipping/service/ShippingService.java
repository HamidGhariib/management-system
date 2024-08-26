package com.management.system.shipping.service;


import com.management.system.shipping.dto.request.InventoryReqDto;
import com.management.system.shipping.dto.response.OrderResDto;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ShippingService {

    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final Logger logger = Logger.getLogger(ShippingService.class.getName());

    public ShippingService(OrderService orderService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    public OrderResDto updateInventory(InventoryReqDto inventoryReqDto) {
        return null;
    }
}
