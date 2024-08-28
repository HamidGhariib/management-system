package com.management.system.inventory.controller;

import com.management.system.inventory.dto.request.NewProductReqDto;
import com.management.system.inventory.dto.response.NewProductResDto;
import com.management.system.inventory.service.InventoryService;
import com.management.system.inventory.dto.request.InventoryUpdateReqDto;
import com.management.system.inventory.dto.response.OrderResDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(path = "/create-new-product",method = RequestMethod.POST)
    public NewProductResDto createNewProduct(NewProductReqDto newProductReqDto){
        return inventoryService.createNewProduct(newProductReqDto);
    }
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public OrderResDto updateInventory(InventoryUpdateReqDto inventoryUpdateReqDto){
        return inventoryService.updateInventory(inventoryUpdateReqDto);
    }
}
