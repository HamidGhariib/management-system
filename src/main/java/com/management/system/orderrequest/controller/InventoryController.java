package com.management.system.orderrequest.controller;

import com.management.system.orderrequest.dto.request.InventoryReqDto;
import com.management.system.orderrequest.dto.request.OrderReqDto;
import com.management.system.orderrequest.dto.response.OrderResDto;
import com.management.system.orderrequest.service.InventoryService;
import com.management.system.orderrequest.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public OrderResDto updateInventory(InventoryReqDto inventoryReqDto){
        return inventoryService.updateInventory(inventoryReqDto);
    }
}
