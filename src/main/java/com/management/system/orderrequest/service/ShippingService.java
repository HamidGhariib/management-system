package com.management.system.orderrequest.service;


import com.management.system.orderrequest.dto.request.InventoryReqDto;
import com.management.system.orderrequest.dto.response.OrderResDto;
import com.management.system.orderrequest.model.Inventory;
import com.management.system.orderrequest.repository.InventoryRepo;
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
