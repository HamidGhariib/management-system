package com.management.system.inventory.service;


import com.management.system.inventory.dto.request.InventoryReqDto;
import com.management.system.inventory.dto.response.OrderResDto;
import com.management.system.inventory.model.Inventory;
import com.management.system.inventory.repository.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public OrderResDto updateInventory(InventoryReqDto inventoryReqDto) {
        logger.info("Update inventory by id "+inventoryReqDto);
        OrderResDto orderResDto = new OrderResDto();
        String orderId = inventoryRepo.save(Inventory.builder().build()).getId().toString();
        orderResDto.setOrderId(orderId);
        return orderResDto;
    }
}
